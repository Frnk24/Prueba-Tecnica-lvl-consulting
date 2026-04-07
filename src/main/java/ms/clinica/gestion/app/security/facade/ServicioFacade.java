package ms.clinica.gestion.app.security.facade;

import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.servicio.ServicioFiltroRequest;
import ms.clinica.gestion.dto.model.servicio.ServicioRequest;
import ms.clinica.gestion.dto.model.servicio.ServicioResponse;
public interface ServicioFacade {
    ServicioResponse getByIdServicio(Long idServicio);
    BaseOperacionResponse saveOrUpdate(ServicioRequest request);
    BaseOperacionResponse deleteServicio(Long idServicio);
    CollectionResponse<ServicioResponse> findServicio(ServicioFiltroRequest filtro);
}
