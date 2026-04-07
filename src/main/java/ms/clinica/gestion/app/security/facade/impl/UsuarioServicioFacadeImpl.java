package ms.clinica.gestion.app.security.facade.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.clinica.gestion.app.security.domain.UsuarioServicio;
import ms.clinica.gestion.app.security.facade.UsuarioServicioFacade;
import ms.clinica.gestion.app.security.service.UsuarioServicioService;
import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.usuarioservicio.UsuarioServicioFiltroRequest;
import ms.clinica.gestion.dto.model.usuarioservicio.UsuarioServicioRequest;
import ms.clinica.gestion.dto.model.usuarioservicio.UsuarioServicioResponse;
import ms.clinica.gestion.dto.util.Constantes;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UsuarioServicioFacadeImpl implements UsuarioServicioFacade {

    private final UsuarioServicioService usuarioServicioService;
    private final ModelMapper modelMapper;

    @Override
    public UsuarioServicioResponse getByIdUsuarioServicio(Long usuarioServicioId) {
        UsuarioServicio us = usuarioServicioService.get(usuarioServicioId);
        return toResponse(us);
    }

    @Override
    public BaseOperacionResponse saveOrUpdate(UsuarioServicioRequest request) {
        usuarioServicioService.saveOrUpdate(request);
        return new BaseOperacionResponse(Constantes.SUCCESS, Constantes.MESSAGE_SAVE);
    }

    @Override
    public BaseOperacionResponse deleteUsuarioServicio(Long usuarioServicioId) {
        usuarioServicioService.delete(usuarioServicioId);
        return new BaseOperacionResponse(Constantes.SUCCESS, Constantes.MESSAGE_DELETE);
    }

    @Override
    public CollectionResponse<UsuarioServicioResponse> findUsuarioServicio(
            UsuarioServicioFiltroRequest filtro) {
        List<UsuarioServicioResponse> collection = new ArrayList<>();
        List<UsuarioServicio> lista = usuarioServicioService.find(filtro);
        lista.forEach(us -> collection.add(toResponse(us)));
        return new CollectionResponse<>(collection, filtro.getStart(),
                filtro.getLimit(), filtro.getTotalCount());
    }

    private UsuarioServicioResponse toResponse(UsuarioServicio us) {
        UsuarioServicioResponse response = new UsuarioServicioResponse();
        response.setUsuarioServicioId(us.getUsuarioServicioId());
        response.setHabilitado(us.getHabilitado());
        if (us.getUsuario() != null) {
            response.setUsuarioId(us.getUsuario().getUsuarioId());
            response.setUsuarioNombre(us.getUsuario().getNombre());
            response.setUsuarioApellido(us.getUsuario().getApellido());
        }
        if (us.getServicio() != null) {
            response.setServicioId(us.getServicio().getServicioId());
            response.setServicioNombre(us.getServicio().getNombre());
        }
        return response;
    }
}