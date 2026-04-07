package ms.clinica.gestion.app.security.facade;

import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.usuario.UsuarioFiltroRequest;
import ms.clinica.gestion.dto.model.usuario.UsuarioRequest;
import ms.clinica.gestion.dto.model.usuario.UsuarioResponse;

public interface UsuarioFacade {
    UsuarioResponse getByIdUsuario(Long idUsuario);
    BaseOperacionResponse saveOrUpdate(UsuarioRequest request);
    BaseOperacionResponse deleteUsuario(Long idUsuario);
    CollectionResponse<UsuarioResponse> findUsuario(UsuarioFiltroRequest filtro);
}
