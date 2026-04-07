package ms.clinica.gestion.app.security.facade;

import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.usuarioservicio.UsuarioServicioFiltroRequest;
import ms.clinica.gestion.dto.model.usuarioservicio.UsuarioServicioRequest;
import ms.clinica.gestion.dto.model.usuarioservicio.UsuarioServicioResponse;

public interface UsuarioServicioFacade {
    UsuarioServicioResponse getByIdUsuarioServicio(Long usuarioServicioId);
    BaseOperacionResponse saveOrUpdate(UsuarioServicioRequest request);
    BaseOperacionResponse deleteUsuarioServicio(Long usuarioServicioId);
    CollectionResponse<UsuarioServicioResponse> findUsuarioServicio(
            UsuarioServicioFiltroRequest filtro);
}