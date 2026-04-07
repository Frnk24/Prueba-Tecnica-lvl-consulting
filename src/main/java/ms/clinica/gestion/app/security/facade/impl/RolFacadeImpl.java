package ms.clinica.gestion.app.security.facade.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.clinica.gestion.app.security.domain.Rol;
import ms.clinica.gestion.app.security.facade.RolFacade;
import ms.clinica.gestion.app.security.service.RolService;
import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.rol.RolFiltroRequest;
import ms.clinica.gestion.dto.model.rol.RolRequest;
import ms.clinica.gestion.dto.model.rol.RolResponse;
import ms.clinica.gestion.dto.util.Constantes;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class RolFacadeImpl implements RolFacade {

    private final RolService rolService;
    private final ModelMapper modelMapper;

    @Override
    public RolResponse getByIdRol(Long rolId){
        Rol rol = rolService.get(rolId);
        return modelMapper.map(rol, RolResponse.class);
    }

    @Override
    public BaseOperacionResponse saveOrUpdate(RolRequest request){
        rolService.saveOrUpdate(request);
        return new BaseOperacionResponse(Constantes.SUCCESS, Constantes.MESSAGE_SAVE);
    }

    @Override
    public BaseOperacionResponse deleteRol(Long rolId) {
        rolService.delete(rolId);
        return new BaseOperacionResponse(Constantes.SUCCESS, Constantes.MESSAGE_DELETE);
    }

    @Override
    public CollectionResponse<RolResponse> findRol(RolFiltroRequest filtro){
        List<RolResponse> collection = new ArrayList<>();
        List<Rol> lista = rolService.find(filtro);
        lista.forEach(rol ->
                collection.add(modelMapper.map(rol, RolResponse.class))
                );
        return new CollectionResponse<>(collection, filtro.getStart(), filtro.getLimit(), filtro.getTotalCount());
    }
    }

