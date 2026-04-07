package ms.clinica.gestion.app.configuration.facade.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.clinica.gestion.app.configuration.domain.Catalogo;
import ms.clinica.gestion.app.configuration.facade.CatalogoFacade;
import ms.clinica.gestion.app.configuration.service.CatalogoService;
import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.catalogo.CatalogoFiltroRequest;
import ms.clinica.gestion.dto.model.catalogo.CatalogoRequest;
import ms.clinica.gestion.dto.model.catalogo.CatalogoResponse;
import ms.clinica.gestion.dto.util.Constantes;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CatalogoFacadeImpl implements CatalogoFacade {
    private final CatalogoService catalogoService;
    private final ModelMapper modelMapper;

    @Override
    public CatalogoResponse getByIdCatalogo(Long catalogoId){
        Catalogo catalogo = catalogoService.get(catalogoId);
        return modelMapper.map(catalogo, CatalogoResponse.class);
    }

    @Override
    public BaseOperacionResponse saveOrUpdateCatalogo(CatalogoRequest request){
        catalogoService.saveOrUpdate(request);
        return new BaseOperacionResponse(Constantes.SUCCESS, Constantes.MESSAGE_SAVE);
    }

    @Override
    public BaseOperacionResponse deleteCatalogo(Long CatalogoId){
        catalogoService.delete(CatalogoId);
        return new BaseOperacionResponse(Constantes.SUCCESS, Constantes.MESSAGE_DELETE);
    }

    @Override
    public CollectionResponse<CatalogoResponse> findCatalogo(CatalogoFiltroRequest filtro){
        List<CatalogoResponse> collection = new ArrayList<>();
        List<Catalogo> lista = catalogoService.find(filtro);
        lista.forEach(catalogo ->
                collection.add(modelMapper.map(catalogo, CatalogoResponse.class))
        );
        return new CollectionResponse<>(collection, filtro.getStart(), filtro.getLimit(), filtro.getTotalCount());
    }

}
