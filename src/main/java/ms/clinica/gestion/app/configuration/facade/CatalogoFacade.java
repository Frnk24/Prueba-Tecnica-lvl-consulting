package ms.clinica.gestion.app.configuration.facade;

import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.catalogo.CatalogoFiltroRequest;
import ms.clinica.gestion.dto.model.catalogo.CatalogoRequest;
import ms.clinica.gestion.dto.model.catalogo.CatalogoResponse;

public interface CatalogoFacade {

    CatalogoResponse getByIdCatalogo(Long catalogoId);
    BaseOperacionResponse saveOrUpdateCatalogo(CatalogoRequest request);
    BaseOperacionResponse deleteCatalogo(Long catalogoId);
    CollectionResponse<CatalogoResponse> findCatalogo(CatalogoFiltroRequest filtro);

}
