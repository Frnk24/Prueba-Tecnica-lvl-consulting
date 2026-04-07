package ms.clinica.gestion.app.security.service.impl;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.clinica.gestion.app.security.domain.Rol;
import ms.clinica.gestion.app.security.repository.RolRepository;
import ms.clinica.gestion.app.security.service.RolService;
import ms.clinica.gestion.core.exception.BadRequestException;
import ms.clinica.gestion.core.exception.NotFoundException;
import ms.clinica.gestion.dto.model.rol.RolFiltroRequest;
import ms.clinica.gestion.dto.model.rol.RolRequest;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Rol> find(RolFiltroRequest filtro) {
        return rolRepository.findAll((Specification<Rol>)(root,query,cb) ->{
            List<Predicate> predicates=new ArrayList<>();

            if (filtro.getNombre() !=null && !filtro.getNombre().trim().isEmpty()){
                predicates.add(cb.like(cb.upper(root.get("nombre")),
                        "%" + filtro.getNombre().toUpperCase() +"%"));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

    @Override
    @Transactional(readOnly = true)
    public Rol get(Long rolId) {
        log.debug("Get Rol:: {}", rolId);
        return rolRepository.findById(rolId)
                .orElseThrow(() -> new NotFoundException("Rol no encontrado: "+ rolId));
    }

    @Override
    @Transactional
    public void saveOrUpdate(RolRequest request){
        if (request.getRolId() != null && request.getRolId() >0){
            Rol rol= this.get(request.getRolId());
            rol.setNombre(request.getNombre());
            rol.setDescripcion(request.getDescripcion());
            rolRepository.save(rol);
        }else{
            rolRepository.findByNombre(request.getNombre()).ifPresent(existente ->{
                throw new BadRequestException("Ya existe un rol con el nombre: "+request.getNombre());
            });

            Rol rol = modelMapper.map(request, Rol.class);
            rolRepository.save(rol);

        }
    }

    @Override
    @Transactional
    public void delete(Long rolId){
        Rol rol= this.get(rolId);
        rol.setHabilitado((short) 0);
        rolRepository.save(rol);
    }
}
