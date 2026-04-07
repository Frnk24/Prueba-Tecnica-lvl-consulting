package ms.clinica.gestion.app.atencion.service.impl;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.clinica.gestion.app.atencion.domain.Atencion;
import ms.clinica.gestion.app.atencion.domain.Evidencia;
import ms.clinica.gestion.app.atencion.domain.Receta;
import ms.clinica.gestion.app.atencion.repository.AtencionRepository;
import ms.clinica.gestion.app.atencion.repository.EvidenciaRepository;
import ms.clinica.gestion.app.atencion.repository.RecetaRepository;
import ms.clinica.gestion.app.atencion.service.AtencionService;
import ms.clinica.gestion.app.cita.domain.Cita;
import ms.clinica.gestion.app.cita.repository.CitaRepository;
import ms.clinica.gestion.core.exception.BadRequestException;
import ms.clinica.gestion.core.exception.NotFoundException;
import ms.clinica.gestion.dto.model.atencion.AtencionFiltroRequest;
import ms.clinica.gestion.dto.model.atencion.AtencionRequest;
import ms.clinica.gestion.dto.model.atencion.EvidenciaRequest;
import ms.clinica.gestion.dto.model.atencion.RecetaRequest;
import ms.clinica.gestion.dto.util.Constantes;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AtencionServiceImpl implements AtencionService {

    private final AtencionRepository atencionRepository;
    private final RecetaRepository recetaRepository;
    private final EvidenciaRepository evidenciaRepository;
    private final CitaRepository citaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Atencion> find(AtencionFiltroRequest filtro) {
        return atencionRepository.findAll((Specification<Atencion>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getCitaId() != null && filtro.getCitaId() > 0) {
                predicates.add(cb.equal(
                        root.get("cita").get("citaId"), filtro.getCitaId()));
            }
            if (filtro.getPacienteId() != null && filtro.getPacienteId() > 0) {
                predicates.add(cb.equal(
                        root.get("cita").get("paciente").get("pacienteId"),
                        filtro.getPacienteId()));
            }
            if (filtro.getMedicoId() != null && filtro.getMedicoId() > 0) {
                predicates.add(cb.equal(
                        root.get("cita").get("programacion")
                                .get("usuarioServicio").get("usuario").get("usuarioId"),
                        filtro.getMedicoId()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

    @Override
    @Transactional(readOnly = true)
    public Atencion get(Long atencionId) {
        log.debug("Get Atencion: {}", atencionId);
        return atencionRepository.findById(atencionId)
                .orElseThrow(() -> new NotFoundException(
                        "Atención no encontrada con id: " + atencionId));
    }

    @Override
    @Transactional
    public void saveOrUpdate(AtencionRequest request) {
        if (request.getAtencionId() != null && request.getAtencionId() > 0) {
            Atencion atencion = this.get(request.getAtencionId());
            atencion.setMotivoConsulta(request.getMotivoConsulta());
            atencion.setDiagnostico(request.getDiagnostico());
            atencion.setTratamiento(request.getTratamiento());
            atencion.setObservacion(request.getObservacion());
            atencionRepository.save(atencion);
        } else {
            Cita cita = citaRepository.findById(request.getCitaId())
                    .orElseThrow(() -> new NotFoundException(
                            "Cita no encontrada con id: " + request.getCitaId()));

            if (!Constantes.EstadoCita.EN_TRIAJE.equals(cita.getEstadoCodigo())) {
                throw new BadRequestException(
                        "Solo se puede registrar atención para citas en estado EN TRIAJE");
            }

            atencionRepository.findByCitaId(request.getCitaId()).ifPresent(a -> {
                throw new BadRequestException(
                        "Ya existe una atención registrada para esta cita");
            });

            Atencion atencion = new Atencion();
            atencion.setCita(cita);
            atencion.setMotivoConsulta(request.getMotivoConsulta());
            atencion.setDiagnostico(request.getDiagnostico());
            atencion.setTratamiento(request.getTratamiento());
            atencion.setObservacion(request.getObservacion());

            cita.setEstadoCodigo(Constantes.EstadoCita.EN_CONSULTA);
            citaRepository.save(cita);
            atencionRepository.save(atencion);
        }
    }

    @Override
    @Transactional
    public void saveOrUpdateReceta(RecetaRequest request) {
        if (request.getRecetaId() != null && request.getRecetaId() > 0) {
            Receta receta = recetaRepository.findById(request.getRecetaId())
                    .orElseThrow(() -> new NotFoundException(
                            "Receta no encontrada con id: " + request.getRecetaId()));
            receta.setMedicamento(request.getMedicamento());
            receta.setDosis(request.getDosis());
            receta.setFrecuencia(request.getFrecuencia());
            receta.setDuracion(request.getDuracion());
            recetaRepository.save(receta);
        } else {
            Atencion atencion = this.get(request.getAtencionId());
            Receta receta = new Receta();
            receta.setAtencion(atencion);
            receta.setMedicamento(request.getMedicamento());
            receta.setDosis(request.getDosis());
            receta.setFrecuencia(request.getFrecuencia());
            receta.setDuracion(request.getDuracion());
            recetaRepository.save(receta);
        }
    }

    @Override
    @Transactional
    public void saveOrUpdateEvidencia(EvidenciaRequest request) {
        if (request.getEvidenciaId() != null && request.getEvidenciaId() > 0) {
            Evidencia evidencia = evidenciaRepository.findById(request.getEvidenciaId())
                    .orElseThrow(() -> new NotFoundException(
                            "Evidencia no encontrada con id: " + request.getEvidenciaId()));
            evidencia.setUrl(request.getUrl());
            evidencia.setDescripcion(request.getDescripcion());
            evidenciaRepository.save(evidencia);
        } else {
            Atencion atencion = this.get(request.getAtencionId());
            Evidencia evidencia = new Evidencia();
            evidencia.setAtencion(atencion);
            evidencia.setUrl(request.getUrl());
            evidencia.setDescripcion(request.getDescripcion());
            evidenciaRepository.save(evidencia);
        }
    }

    @Override
    @Transactional
    public void deleteReceta(Long recetaId) {
        Receta receta = recetaRepository.findById(recetaId)
                .orElseThrow(() -> new NotFoundException(
                        "Receta no encontrada con id: " + recetaId));
        receta.setHabilitado((short) 0);
        recetaRepository.save(receta);
    }

    @Override
    @Transactional
    public void deleteEvidencia(Long evidenciaId) {
        Evidencia evidencia = evidenciaRepository.findById(evidenciaId)
                .orElseThrow(() -> new NotFoundException(
                        "Evidencia no encontrada con id: " + evidenciaId));
        evidencia.setHabilitado((short) 0);
        evidenciaRepository.save(evidencia);
    }

    @Override
    @Transactional
    public void finalizar(Long atencionId) {
        Atencion atencion = this.get(atencionId);
        if (!Constantes.EstadoCita.EN_CONSULTA.equals(
                atencion.getCita().getEstadoCodigo())) {
            throw new BadRequestException(
                    "Solo se puede finalizar una atención en estado EN CONSULTA");
        }
        atencion.getCita().setEstadoCodigo(Constantes.EstadoCita.FINALIZADO);
        citaRepository.save(atencion.getCita());
    }

    @Override
    @Transactional
    public void delete(Long atencionId) {
        Atencion atencion = this.get(atencionId);
        atencion.setHabilitado((short) 0);
        atencionRepository.save(atencion);
    }
}