package ms.clinica.gestion.app.configuration.service.impl;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.clinica.gestion.app.configuration.domain.Catalogo;
import ms.clinica.gestion.app.configuration.repository.CatalogoRepository;
import ms.clinica.gestion.app.configuration.service.CatalogoService;
import ms.clinica.gestion.core.exception.BadRequestException;
import ms.clinica.gestion.core.exception.NotFoundException;
import ms.clinica.gestion.dto.model.catalogo.CatalogoFiltroRequest;
import ms.clinica.gestion.dto.model.catalogo.CatalogoRequest;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CatalogoServiceImpl implements CatalogoService {

    private final CatalogoRepository catalogoRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Catalogo> find(CatalogoFiltroRequest filtro){
        return catalogoRepository.findAll((Specification<Catalogo>) (root, query, cb) ->{
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getNombre() != null && !filtro.getNombre().trim().isEmpty()){
                predicates.add(cb.like(cb.upper(root.get("nombre")),
                "%" + filtro.getNombre().toUpperCase() + "%"));
            }
            if (filtro.getPrefijo() != null && !filtro.getPrefijo().trim().isEmpty()) {
                predicates.add(cb.like(cb.upper(root.get("prefijo")),
                        "%" + filtro.getPrefijo().toUpperCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

    @Override
    @Transactional(readOnly = true)
    public Catalogo get(Long catalogoId) {
        log.debug("Get Catalogo:: {}", catalogoId);
        return catalogoRepository.findById(catalogoId)
                .orElseThrow(() -> new NotFoundException("Catalogo no encontrado: "+ catalogoId));
    }

    @Override
    @Transactional
    public void saveOrUpdate(CatalogoRequest request){
        if (request.getCatalogoId() != null && request.getCatalogoId() > 0) {
            Catalogo catalogo = this.get(request.getCatalogoId());
            catalogo.setNombre(request.getNombre());
            catalogo.setDescripcion(request.getDescripcion());
            catalogo.setValor(request.getValor());
            catalogo.setPrefijo(request.getPrefijo());
            catalogoRepository.save(catalogo);
        } else {
            catalogoRepository.getByCodigo(request.getCodigo()).ifPresent(existente -> {
                throw new BadRequestException("Ya existe un catálogo con el código: "
                        + request.getCodigo());
            });
            Catalogo catalogo = modelMapper.map(request, Catalogo.class);
            catalogoRepository.save(catalogo);
        }
    }

    @Override
    @Transactional
    public void delete(Long catalogoId) {
        Catalogo catalogo = this.get(catalogoId);
        catalogo.setHabilitado((short) 0);
        catalogoRepository.save(catalogo);
    }
}
