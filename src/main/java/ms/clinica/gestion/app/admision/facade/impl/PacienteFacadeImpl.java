package ms.clinica.gestion.app.admision.facade.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.clinica.gestion.app.admision.domain.Paciente;
import ms.clinica.gestion.app.admision.facade.PacienteFacade;
import ms.clinica.gestion.app.admision.service.PacienteService;
import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.paciente.PacienteFiltroRequest;
import ms.clinica.gestion.dto.model.paciente.PacienteRequest;
import ms.clinica.gestion.dto.model.paciente.PacienteResponse;
import ms.clinica.gestion.dto.util.Constantes;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PacienteFacadeImpl implements PacienteFacade {

    private final PacienteService pacienteService;
    private final ModelMapper modelMapper;

    @Override
    public PacienteResponse getByIdPaciente(Long pacienteId) {
        Paciente paciente = pacienteService.get(pacienteId);
        return toResponse(paciente);
    }

    @Override
    public BaseOperacionResponse saveOrUpdate(PacienteRequest request) {
        pacienteService.saveOrUpdate(request);
        return new BaseOperacionResponse(Constantes.SUCCESS, Constantes.MESSAGE_SAVE);
    }

    @Override
    public BaseOperacionResponse deletePaciente(Long pacienteId) {
        pacienteService.delete(pacienteId);
        return new BaseOperacionResponse(Constantes.SUCCESS, Constantes.MESSAGE_DELETE);
    }

    @Override
    public CollectionResponse<PacienteResponse> findPaciente(PacienteFiltroRequest filtro) {
        List<PacienteResponse> collection = new ArrayList<>();
        List<Paciente> lista = pacienteService.find(filtro);
        lista.forEach(paciente -> collection.add(toResponse(paciente)));
        return new CollectionResponse<>(collection, filtro.getStart(),
                filtro.getLimit(), filtro.getTotalCount());
    }

    private PacienteResponse toResponse(Paciente paciente) {
        PacienteResponse response = modelMapper.map(paciente, PacienteResponse.class);
        if (paciente.getUsuario() != null) {
            response.setUsuarioId(paciente.getUsuario().getUsuarioId());
        }
        return response;
    }
}