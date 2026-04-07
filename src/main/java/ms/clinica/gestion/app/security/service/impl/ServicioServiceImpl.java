package ms.clinica.gestion.app.security.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.clinica.gestion.app.security.domain.Servicio;
import ms.clinica.gestion.app.security.repository.ServicioRepository;
import ms.clinica.gestion.app.security.service.ServicioService;
import ms.clinica.gestion.core.exception.BadRequestException;
import ms.clinica.gestion.core.exception.NotFoundException;
import ms.clinica.gestion.dto.model.servicio.ServicioFiltroRequest;
import ms.clinica.gestion.dto.model.servicio.ServicioRequest;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository servicioRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> find(ServicioFiltroRequest filtro) {
        return servicioRepository.findAll((Specification<Servicio>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getNombre() != null && !filtro.getNombre().trim().isEmpty()) {
                predicates.add(cb.like(cb.upper(root.get("nombre")),
                        "%" + filtro.getNombre().toUpperCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

    @Override
    @Transactional(readOnly = true)
    public Servicio get(Long servicioId) {
        log.debug("Get Servicio: {}", servicioId);
        return servicioRepository.findById(servicioId)
                .orElseThrow(() -> new NotFoundException("Servicio no encontrado con id: " + servicioId));
    }

    @Override
    @Transactional
    public void saveOrUpdate(ServicioRequest request) {
        if (request.getServicioId() != null && request.getServicioId() > 0) {
            Servicio servicio = this.get(request.getServicioId());
            servicio.setNombre(request.getNombre());
            servicio.setDescripcion(request.getDescripcion());
            servicio.setPrecio(request.getPrecio());
            servicio.setDuracion(request.getDuracion());
            servicio.setTiempoTolerancia(request.getTiempoTolerancia());
            servicioRepository.save(servicio);
        } else {
            servicioRepository.findByNombre(request.getNombre()).ifPresent(existente -> {
                throw new BadRequestException("Ya existe un servicio con el nombre: "
                        + request.getNombre());
            });
            Servicio servicio = modelMapper.map(request, Servicio.class);
            servicioRepository.save(servicio);
        }
    }

    @Override
    @Transactional
    public void delete(Long servicioId) {
        Servicio servicio = this.get(servicioId);
        servicio.setHabilitado((short) 0);
        servicioRepository.save(servicio);
    }
}
