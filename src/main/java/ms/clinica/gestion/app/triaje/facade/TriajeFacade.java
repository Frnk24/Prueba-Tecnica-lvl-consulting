package ms.clinica.gestion.app.triaje.facade;

import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.triaje.TriajeFiltroRequest;
import ms.clinica.gestion.dto.model.triaje.TriajeRequest;
import ms.clinica.gestion.dto.model.triaje.TriajeResponse;

public interface TriajeFacade {
    TriajeResponse getByIdTriaje(Long triajeId);
    BaseOperacionResponse saveOrUpdate(TriajeRequest request);
    BaseOperacionResponse deleteTriaje(Long triajeId);
    CollectionResponse<TriajeResponse> findTriaje(TriajeFiltroRequest filtro);
}