package ms.clinica.gestion.app.triaje.service.impl;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.clinica.gestion.app.cita.domain.Cita;
import ms.clinica.gestion.app.cita.repository.CitaRepository;
import ms.clinica.gestion.app.triaje.domain.Triaje;
import ms.clinica.gestion.app.triaje.repository.TriajeRepository;
import ms.clinica.gestion.app.triaje.service.TriajeService;
import ms.clinica.gestion.core.exception.BadRequestException;
import ms.clinica.gestion.core.exception.NotFoundException;
import ms.clinica.gestion.dto.model.triaje.TriajeFiltroRequest;
import ms.clinica.gestion.dto.model.triaje.TriajeRequest;
import ms.clinica.gestion.dto.util.Constantes;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TriajeServiceImpl implements TriajeService {

    private final TriajeRepository triajeRepository;
    private final CitaRepository citaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Triaje> find(TriajeFiltroRequest filtro) {
        return triajeRepository.findAll((Specification<Triaje>) (root, query, cb) -> {
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

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

    @Override
    @Transactional(readOnly = true)
    public Triaje get(Long triajeId) {
        log.debug("Get Triaje: {}", triajeId);
        return triajeRepository.findById(triajeId)
                .orElseThrow(() -> new NotFoundException(
                        "Triaje no encontrado con id: " + triajeId));
    }

    @Override
    @Transactional
    public void saveOrUpdate(TriajeRequest request) {
        if (request.getTriajeId() != null && request.getTriajeId() > 0) {
            Triaje triaje = this.get(request.getTriajeId());
            triaje.setTalla(request.getTalla());
            triaje.setPeso(request.getPeso());
            triaje.setPresion(request.getPresion());
            triaje.setTemperatura(request.getTemperatura());
            triaje.setFrecuenciaCardiaca(request.getFrecuenciaCardiaca());
            triaje.setSaturacion(request.getSaturacion());
            triajeRepository.save(triaje);
        } else {
            Cita cita = citaRepository.findById(request.getCitaId())
                    .orElseThrow(() -> new NotFoundException(
                            "Cita no encontrada con id: " + request.getCitaId()));

            if (!Constantes.EstadoCita.EN_ESPERA.equals(cita.getEstadoCodigo())) {
                throw new BadRequestException(
                        "Solo se puede registrar triaje para citas en estado EN ESPERA");
            }

            triajeRepository.findByCitaId(request.getCitaId()).ifPresent(t -> {
                throw new BadRequestException(
                        "Ya existe un triaje registrado para esta cita");
            });

            Triaje triaje = new Triaje();
            triaje.setCita(cita);
            triaje.setTalla(request.getTalla());
            triaje.setPeso(request.getPeso());
            triaje.setPresion(request.getPresion());
            triaje.setTemperatura(request.getTemperatura());
            triaje.setFrecuenciaCardiaca(request.getFrecuenciaCardiaca());
            triaje.setSaturacion(request.getSaturacion());

            cita.setEstadoCodigo(Constantes.EstadoCita.EN_TRIAJE);
            citaRepository.save(cita);
            triajeRepository.save(triaje);
        }
    }

    @Override
    @Transactional
    public void delete(Long triajeId) {
        Triaje triaje = this.get(triajeId);
        triaje.setHabilitado((short) 0);
        triajeRepository.save(triaje);
    }
}