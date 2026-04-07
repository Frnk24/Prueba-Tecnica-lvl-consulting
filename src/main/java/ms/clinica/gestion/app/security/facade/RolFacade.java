package ms.clinica.gestion.app.security.facade;

import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.rol.RolFiltroRequest;
import ms.clinica.gestion.dto.model.rol.RolRequest;
import ms.clinica.gestion.dto.model.rol.RolResponse;

public interface RolFacade {
    RolResponse getByIdRol(Long idRol);
    BaseOperacionResponse saveOrUpdate(RolRequest request);
    BaseOperacionResponse deleteRol(Long idRol);
    CollectionResponse<RolResponse> findRol(RolFiltroRequest filtro);
}
