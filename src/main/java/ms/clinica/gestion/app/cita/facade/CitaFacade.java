package ms.clinica.gestion.app.cita.facade;

import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.cita.CitaFiltroRequest;
import ms.clinica.gestion.dto.model.cita.CitaRequest;
import ms.clinica.gestion.dto.model.cita.CitaResponse;

public interface CitaFacade {
    CitaResponse getByIdCita(Long citaId);
    BaseOperacionResponse saveOrUpdate(CitaRequest request);
    BaseOperacionResponse cancelarCita(Long citaId);
    BaseOperacionResponse deleteCita(Long citaId);
    CollectionResponse<CitaResponse> findCita(CitaFiltroRequest filtro);
}