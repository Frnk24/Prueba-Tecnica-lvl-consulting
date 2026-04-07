package ms.clinica.gestion.app.programacion.facade;

import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.programacion.ProgramacionFiltroRequest;
import ms.clinica.gestion.dto.model.programacion.ProgramacionRequest;
import ms.clinica.gestion.dto.model.programacion.ProgramacionResponse;

public interface ProgramacionFacade {
    ProgramacionResponse getByIdProgramacion(Long programacionId);
    BaseOperacionResponse saveOrUpdate(ProgramacionRequest request);
    BaseOperacionResponse delete(Long programacionId);
    CollectionResponse<ProgramacionResponse> findProgramacion(ProgramacionFiltroRequest filtro);
}
