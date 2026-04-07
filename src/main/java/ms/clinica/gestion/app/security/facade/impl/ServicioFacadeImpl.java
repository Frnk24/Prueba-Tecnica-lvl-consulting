package ms.clinica.gestion.app.security.facade.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.clinica.gestion.app.security.domain.Servicio;
import ms.clinica.gestion.app.security.facade.ServicioFacade;
import ms.clinica.gestion.app.security.service.ServicioService;
import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.servicio.ServicioFiltroRequest;
import ms.clinica.gestion.dto.model.servicio.ServicioRequest;
import ms.clinica.gestion.dto.model.servicio.ServicioResponse;
import ms.clinica.gestion.dto.util.Constantes;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ServicioFacadeImpl implements ServicioFacade {
    private final ServicioService servicioService;
    private final ModelMapper modelMapper;

    @Override
    public ServicioResponse getByIdServicio(Long idServicio){
        Servicio servicio = servicioService.get(idServicio);
        return modelMapper.map(servicio, ServicioResponse.class);
    }

    @Override
    public BaseOperacionResponse saveOrUpdate(ServicioRequest request){
        servicioService.saveOrUpdate(request);
        return new BaseOperacionResponse(Constantes.SUCCESS, Constantes.MESSAGE_SAVE);
    }

    @Override
    public BaseOperacionResponse deleteServicio(Long idServicio){
        servicioService.delete(idServicio);
        return  new BaseOperacionResponse(Constantes.SUCCESS, Constantes.MESSAGE_DELETE);
    }

    @Override
    public CollectionResponse<ServicioResponse> findServicio(ServicioFiltroRequest filtro){
     List<ServicioResponse> collection = new ArrayList<>();
     List<Servicio> lista = servicioService.find(filtro);
     lista.forEach(servicio ->
             collection.add(modelMapper.map(servicio, ServicioResponse.class))
             );
     return new CollectionResponse<>(collection, filtro.getStart(),filtro.getLimit(),filtro.getTotalCount());
    }
}
