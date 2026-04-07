package ms.clinica.gestion.app.security.facade.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.clinica.gestion.app.security.domain.Usuario;
import ms.clinica.gestion.app.security.facade.UsuarioFacade;
import ms.clinica.gestion.app.security.service.UsuarioService;
import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.usuario.UsuarioFiltroRequest;
import ms.clinica.gestion.dto.model.usuario.UsuarioRequest;
import ms.clinica.gestion.dto.model.usuario.UsuarioResponse;
import ms.clinica.gestion.dto.util.Constantes;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UsuarioFacadeImpl implements UsuarioFacade {
    private final UsuarioService usuarioService;
    private final ModelMapper modelMapper;

    @Override
    public UsuarioResponse getByIdUsuario(Long idUsuario){
        Usuario usuario = usuarioService.get(idUsuario);
        UsuarioResponse response = modelMapper.map(usuario, UsuarioResponse.class);
        if (usuario.getRol() != null) {
            response.setRolId(usuario.getRol().getRolId());
            response.setRolNombre(usuario.getRol().getNombre());
        }
        return response;
    }

    @Override
    public BaseOperacionResponse saveOrUpdate(UsuarioRequest request){
        usuarioService.saveOrUpdate(request);
        return new BaseOperacionResponse(Constantes.SUCCESS, Constantes.MESSAGE_SAVE);
    }

    @Override
    public BaseOperacionResponse deleteUsuario(Long idUsuario){
        usuarioService.delete(idUsuario);
        return new BaseOperacionResponse(Constantes.SUCCESS, Constantes.MESSAGE_DELETE);
    }

    @Override
    public CollectionResponse<UsuarioResponse> findUsuario(UsuarioFiltroRequest filtro){
        List<UsuarioResponse> collection = new ArrayList<>();
        List<Usuario> lista = usuarioService.find(filtro);
        lista.forEach(usuario -> {
            UsuarioResponse response = modelMapper.map(usuario, UsuarioResponse.class);
            if (usuario.getRol() != null) {
                response.setRolId(usuario.getRol().getRolId());
                response.setRolNombre(usuario.getRol().getNombre());
            }
            collection.add(response);
        });
        return new CollectionResponse<>(collection, filtro.getStart(), filtro.getLimit(), filtro.getTotalCount());
    }
}
