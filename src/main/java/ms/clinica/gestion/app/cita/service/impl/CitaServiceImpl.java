package ms.clinica.gestion.app.cita.service.impl;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.clinica.gestion.app.admision.domain.Paciente;
import ms.clinica.gestion.app.admision.repository.PacienteRepository;
import ms.clinica.gestion.app.cita.domain.Cita;
import ms.clinica.gestion.app.cita.repository.CitaRepository;
import ms.clinica.gestion.app.cita.service.CitaService;
import ms.clinica.gestion.app.programacion.domain.Programacion;
import ms.clinica.gestion.app.programacion.repository.ProgramacionRepository;
import ms.clinica.gestion.core.exception.BadRequestException;
import ms.clinica.gestion.core.exception.NotFoundException;
import ms.clinica.gestion.dto.model.cita.CitaFiltroRequest;
import ms.clinica.gestion.dto.model.cita.CitaRequest;
import ms.clinica.gestion.dto.util.Constantes;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;
    private final PacienteRepository pacienteRepository;
    private final ProgramacionRepository programacionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Cita> find(CitaFiltroRequest filtro) {
        return citaRepository.findAll((Specification<Cita>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getPacienteId() != null && filtro.getPacienteId() > 0) {
                predicates.add(cb.equal(
                        root.get("paciente").get("pacienteId"),
                        filtro.getPacienteId()));
            }
            if (filtro.getProgramacionId() != null && filtro.getProgramacionId() > 0) {
                predicates.add(cb.equal(
                        root.get("programacion").get("programacionId"),
                        filtro.getProgramacionId()));
            }
            if (filtro.getUsuarioId() != null && filtro.getUsuarioId() > 0) {
                predicates.add(cb.equal(
                        root.get("programacion")
                                .get("usuarioServicio")
                                .get("usuario")
                                .get("usuarioId"),
                        filtro.getUsuarioId()));
            }
            if (filtro.getEstadoCodigo() != null && !filtro.getEstadoCodigo().trim().isEmpty()) {
                predicates.add(cb.equal(root.get("estadoCodigo"),
                        filtro.getEstadoCodigo()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

    @Override
    @Transactional(readOnly = true)
    public Cita get(Long citaId) {
        log.debug("Get Cita: {}", citaId);
        return citaRepository.findById(citaId)
                .orElseThrow(() -> new NotFoundException(
                        "Cita no encontrada con id: " + citaId));
    }

    @Override
    @Transactional
    public void saveOrUpdate(CitaRequest request) {
        if (request.getCitaId() != null && request.getCitaId() > 0) {
            Cita cita = this.get(request.getCitaId());
            cita.setInconveniente(request.getInconveniente());
            cita.setObservacion(request.getObservacion());
            citaRepository.save(cita);
        } else {
            Programacion programacion = programacionRepository
                    .findById(request.getProgramacionId())
                    .orElseThrow(() -> new NotFoundException(
                            "Programación no encontrada con id: "
                                    + request.getProgramacionId()));

            if (programacion.getCupoOcupado() >= programacion.getCupoTotal()) {
                throw new BadRequestException(
                        "No hay cupos disponibles para esta programación");
            }

            Paciente paciente = pacienteRepository
                    .findById(request.getPacienteId())
                    .orElseThrow(() -> new NotFoundException(
                            "Paciente no encontrado con id: "
                                    + request.getPacienteId()));

            Cita cita = new Cita();
            cita.setPaciente(paciente);
            cita.setProgramacion(programacion);
            cita.setInconveniente(request.getInconveniente());
            cita.setObservacion(request.getObservacion());
            cita.setEstadoCodigo(Constantes.EstadoCita.RESERVADO);

            programacion.setCupoOcupado(programacion.getCupoOcupado() + 1);
            if (programacion.getCupoOcupado() >= programacion.getCupoTotal()) {
                programacion.setEstadoCodigo(Constantes.EstadoProgramacion.COMPLETO);
            }
            programacionRepository.save(programacion);
            citaRepository.save(cita);
        }
    }

    @Override
    @Transactional
    public void cancelar(Long citaId) {
        Cita cita = this.get(citaId);
        if (!Constantes.EstadoCita.RESERVADO.equals(cita.getEstadoCodigo())) {
            throw new BadRequestException(
                    "Solo se puede cancelar una cita en estado RESERVADO");
        }
        cita.setEstadoCodigo(Constantes.EstadoCita.CANCELADO);
        Programacion programacion = cita.getProgramacion();
        programacion.setCupoOcupado(programacion.getCupoOcupado() - 1);
        if (Constantes.EstadoProgramacion.COMPLETO.equals(programacion.getEstadoCodigo())) {
            programacion.setEstadoCodigo(Constantes.EstadoProgramacion.DISPONIBLE);
        }
        programacionRepository.save(programacion);
        citaRepository.save(cita);
    }

    @Override
    @Transactional
    public void delete(Long citaId) {
        Cita cita = this.get(citaId);
        cita.setHabilitado((short) 0);
        citaRepository.save(cita);
    }
}