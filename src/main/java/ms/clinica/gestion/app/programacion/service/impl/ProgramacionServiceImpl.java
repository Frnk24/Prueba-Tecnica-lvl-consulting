package ms.clinica.gestion.app.programacion.service.impl;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.clinica.gestion.app.programacion.domain.Programacion;
import ms.clinica.gestion.app.programacion.repository.ProgramacionRepository;
import ms.clinica.gestion.app.programacion.service.ProgramacionService;
import ms.clinica.gestion.app.security.domain.UsuarioServicio;
import ms.clinica.gestion.app.security.repository.UsuarioRepository;
import ms.clinica.gestion.app.security.repository.UsuarioServicioRepository;
import ms.clinica.gestion.core.exception.BadRequestException;
import ms.clinica.gestion.core.exception.NotFoundException;
import ms.clinica.gestion.dto.model.programacion.ProgramacionFiltroRequest;
import ms.clinica.gestion.dto.model.programacion.ProgramacionRequest;
import ms.clinica.gestion.dto.util.Constantes;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProgramacionServiceImpl implements ProgramacionService {

    private final ProgramacionRepository programacionRepository;
    private final ModelMapper modelMapper;
    private final UsuarioServicioRepository usuarioServicioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Programacion> find(ProgramacionFiltroRequest filtro) {
        return programacionRepository.findAll((Specification<Programacion>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filtro.getUsuarioServicioId() != null && filtro.getUsuarioServicioId() > 0) {
                predicates.add(cb.equal(root.get("usuarioServicio").get("usuarioServicioId"), filtro.getUsuarioServicioId()));
            }
            if (filtro.getUsuarioId() != null && filtro.getUsuarioId() > 0) {
                predicates.add(cb.equal(root.get("usuarioServicio").get("usuario").get("usuarioId"), filtro.getUsuarioId()));
            }
            if (filtro.getServicioId() != null && filtro.getServicioId() > 0) {
                predicates.add(cb.equal(root.get("usuarioServicio").get("servicio").get("servicioId"), filtro.getServicioId()));
            }
            if (filtro.getFecha() != null) {
                predicates.add(cb.equal(root.get("fecha"), filtro.getFecha()));
            }
            if (filtro.getEstadoCodigo() != null && filtro.getEstadoCodigo().trim().isEmpty()) {
                predicates.add(cb.equal(root.get("estadoCodigo"), filtro.getEstadoCodigo()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

    @Override
    @Transactional(readOnly = true)
    public Programacion get(Long programacionId) {
        log.debug("Get Programacion: {}", programacionId);
        return programacionRepository.findById(programacionId)
                .orElseThrow(() -> new NotFoundException("Programacion no econtrada: " + programacionId));
    }

    @Override
    @Transactional
    public void saveOrUpdate(ProgramacionRequest request) {
        if (request.getProgramacionId() != null && request.getProgramacionId() > 0) {
            Programacion programacion = this.get(request.getProgramacionId());
            programacion.setFecha(request.getFecha());
            programacion.setHoraInicio(request.getHoraInicio());
            programacion.setHoraFin(request.getHoraFin());
            programacion.setCupoTotal(request.getCupoTotal());
            programacion.setEstadoCodigo(request.getEstadoCodigo());
            programacionRepository.save(programacion);
        } else {
            validarProgramacion(request);
            UsuarioServicio usuarioServicio = usuarioServicioRepository
                    .findById(request.getUsuarioServicioId())
                    .orElseThrow(() -> new NotFoundException(
                            "Asignación médico-servicio no encontrada con id: "
                                    + request.getUsuarioServicioId()));
            Programacion programacion = new Programacion();
            programacion.setUsuarioServicio(usuarioServicio);
            programacion.setFecha(request.getFecha());
            programacion.setHoraInicio(request.getHoraInicio());
            programacion.setHoraFin(request.getHoraFin());
            programacion.setCupoTotal(request.getCupoTotal());
            programacion.setCupoOcupado(0);
            programacion.setEstadoCodigo(Constantes.EstadoProgramacion.DISPONIBLE);
            programacionRepository.save(programacion);
        }
    }

    @Override
    @Transactional
    public void delete(Long programacionId) {
        Programacion programacion = this.get(programacionId);
        programacion.setHabilitado((short) 0);
        programacionRepository.delete(programacion);
    }

    public void validarProgramacion(ProgramacionRequest request) {

        if (request.getHoraFin().isBefore(request.getHoraInicio()) ||
                request.getHoraFin().equals(request.getHoraInicio())) {
            throw new BadRequestException(
                    "La hora de fin debe ser mayor a la hora de inicio");
        }
        if (request.getCupoTotal() <= 0) {
            throw new BadRequestException(
                    "El cupo total debe ser mayor a 0");
        }
        List<Programacion> existentes = programacionRepository
                .findByUsuarioServicioAndFecha(
                        request.getUsuarioServicioId(), request.getFecha());
        for (Programacion p : existentes) {
            boolean seSolapa =
                    request.getHoraInicio().isBefore(p.getHoraFin()) &&
                            request.getHoraFin().isAfter(p.getHoraInicio());
            if (seSolapa) {
                throw new BadRequestException(
                        "Ya existe una programación en ese horario para este médico y servicio");
            }
        }
    }
}
