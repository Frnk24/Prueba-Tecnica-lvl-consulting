package ms.clinica.gestion.app.triaje.facade.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.clinica.gestion.app.triaje.domain.Triaje;
import ms.clinica.gestion.app.triaje.facade.TriajeFacade;
import ms.clinica.gestion.app.triaje.service.TriajeService;
import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.triaje.TriajeFiltroRequest;
import ms.clinica.gestion.dto.model.triaje.TriajeRequest;
import ms.clinica.gestion.dto.model.triaje.TriajeResponse;
import ms.clinica.gestion.dto.util.Constantes;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TriajeFacadeImpl implements TriajeFacade {

    private final TriajeService triajeService;

    @Override
    public TriajeResponse getByIdTriaje(Long triajeId) {
        return toResponse(triajeService.get(triajeId));
    }

    @Override
    public BaseOperacionResponse saveOrUpdate(TriajeRequest request) {
        triajeService.saveOrUpdate(request);
        return new BaseOperacionResponse(Constantes.SUCCESS, Constantes.MESSAGE_SAVE);
    }

    @Override
    public BaseOperacionResponse deleteTriaje(Long triajeId) {
        triajeService.delete(triajeId);
        return new BaseOperacionResponse(Constantes.SUCCESS, Constantes.MESSAGE_DELETE);
    }

    @Override
    public CollectionResponse<TriajeResponse> findTriaje(TriajeFiltroRequest filtro) {
        List<TriajeResponse> collection = new ArrayList<>();
        List<Triaje> lista = triajeService.find(filtro);
        lista.forEach(triaje -> collection.add(toResponse(triaje)));
        return new CollectionResponse<>(collection, filtro.getStart(),
                filtro.getLimit(), filtro.getTotalCount());
    }

    private TriajeResponse toResponse(Triaje triaje) {
        TriajeResponse response = new TriajeResponse();
        response.setTriajeId(triaje.getTriajeId());
        response.setTalla(triaje.getTalla());
        response.setPeso(triaje.getPeso());
        response.setPresion(triaje.getPresion());
        response.setTemperatura(triaje.getTemperatura());
        response.setFrecuenciaCardiaca(triaje.getFrecuenciaCardiaca());
        response.setSaturacion(triaje.getSaturacion());
        response.setFecha(triaje.getFecha());
        response.setHabilitado(triaje.getHabilitado());
        if (triaje.getCita() != null) {
            response.setCitaId(triaje.getCita().getCitaId());
            if (triaje.getCita().getPaciente() != null) {
                response.setPacienteNombre(
                        triaje.getCita().getPaciente().getNombre());
                response.setPacienteApellido(
                        triaje.getCita().getPaciente().getApellido());
            }
        }
        return response;
    }
}