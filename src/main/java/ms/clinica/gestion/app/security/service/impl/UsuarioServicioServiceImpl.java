package ms.clinica.gestion.app.security.service.impl;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.clinica.gestion.app.security.domain.Servicio;
import ms.clinica.gestion.app.security.domain.Usuario;
import ms.clinica.gestion.app.security.domain.UsuarioServicio;
import ms.clinica.gestion.app.security.repository.ServicioRepository;
import ms.clinica.gestion.app.security.repository.UsuarioRepository;
import ms.clinica.gestion.app.security.repository.UsuarioServicioRepository;
import ms.clinica.gestion.app.security.service.UsuarioServicioService;
import ms.clinica.gestion.core.exception.BadRequestException;
import ms.clinica.gestion.core.exception.NotFoundException;
import ms.clinica.gestion.dto.model.usuarioservicio.UsuarioServicioFiltroRequest;
import ms.clinica.gestion.dto.model.usuarioservicio.UsuarioServicioRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioServicioServiceImpl implements UsuarioServicioService {

    private final UsuarioServicioRepository usuarioServicioRepository;
    private final UsuarioRepository usuarioRepository;
    private final ServicioRepository servicioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioServicio> find(UsuarioServicioFiltroRequest filtro) {
        return usuarioServicioRepository.findAll((Specification<UsuarioServicio>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getUsuarioId() != null && filtro.getUsuarioId() > 0) {
                predicates.add(cb.equal(
                        root.get("usuario").get("usuarioId"), filtro.getUsuarioId()));
            }
            if (filtro.getServicioId() != null && filtro.getServicioId() > 0) {
                predicates.add(cb.equal(
                        root.get("servicio").get("servicioId"), filtro.getServicioId()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioServicio get(Long usuarioServicioId) {
        log.debug("Get UsuarioServicio: {}", usuarioServicioId);
        return usuarioServicioRepository.findById(usuarioServicioId)
                .orElseThrow(() -> new NotFoundException(
                        "Asignación no encontrada con id: " + usuarioServicioId));
    }

    @Override
    @Transactional
    public void saveOrUpdate(UsuarioServicioRequest request) {
        if (request.getUsuarioServicioId() != null && request.getUsuarioServicioId() > 0) {
            UsuarioServicio usuarioServicio = this.get(request.getUsuarioServicioId());
            Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                    .orElseThrow(() -> new NotFoundException(
                            "Usuario no encontrado con id: " + request.getUsuarioId()));
            Servicio servicio = servicioRepository.findById(request.getServicioId())
                    .orElseThrow(() -> new NotFoundException(
                            "Servicio no encontrado con id: " + request.getServicioId()));
            usuarioServicio.setUsuario(usuario);
            usuarioServicio.setServicio(servicio);
            usuarioServicioRepository.save(usuarioServicio);
        } else {
            usuarioServicioRepository
                    .findByUsuarioUsuarioIdAndServicioServicioId(
                            request.getUsuarioId(), request.getServicioId())
                    .ifPresent(existente -> {
                        throw new BadRequestException("El usuario ya tiene asignado ese servicio");
                    });
            Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                    .orElseThrow(() -> new NotFoundException(
                            "Usuario no encontrado con id: " + request.getUsuarioId()));
            Servicio servicio = servicioRepository.findById(request.getServicioId())
                    .orElseThrow(() -> new NotFoundException(
                            "Servicio no encontrado con id: " + request.getServicioId()));
            UsuarioServicio usuarioServicio = new UsuarioServicio();
            usuarioServicio.setUsuario(usuario);
            usuarioServicio.setServicio(servicio);
            usuarioServicioRepository.save(usuarioServicio);
        }
    }

    @Override
    @Transactional
    public void delete(Long usuarioServicioId) {
        UsuarioServicio usuarioServicio = this.get(usuarioServicioId);
        usuarioServicio.setHabilitado((short) 0);
        usuarioServicioRepository.save(usuarioServicio);
    }
}