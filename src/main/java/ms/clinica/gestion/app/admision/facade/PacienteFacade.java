package ms.clinica.gestion.app.admision.facade;

import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.paciente.PacienteFiltroRequest;
import ms.clinica.gestion.dto.model.paciente.PacienteRequest;
import ms.clinica.gestion.dto.model.paciente.PacienteResponse;

public interface PacienteFacade {
    PacienteResponse getByIdPaciente(Long pacienteId);
    BaseOperacionResponse saveOrUpdate(PacienteRequest request);
    BaseOperacionResponse deletePaciente(Long pacienteId);
    CollectionResponse<PacienteResponse> findPaciente(PacienteFiltroRequest filtro);
}