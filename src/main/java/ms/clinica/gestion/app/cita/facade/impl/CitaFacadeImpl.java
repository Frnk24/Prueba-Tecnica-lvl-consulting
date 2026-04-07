package ms.clinica.gestion.app.cita.facade.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.clinica.gestion.app.cita.domain.Cita;
import ms.clinica.gestion.app.cita.facade.CitaFacade;
import ms.clinica.gestion.app.cita.service.CitaService;
import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.cita.CitaFiltroRequest;
import ms.clinica.gestion.dto.model.cita.CitaRequest;
import ms.clinica.gestion.dto.model.cita.CitaResponse;
import ms.clinica.gestion.dto.util.Constantes;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CitaFacadeImpl implements CitaFacade {

    private final CitaService citaService;

    @Override
    public CitaResponse getByIdCita(Long citaId) {
        return toResponse(citaService.get(citaId));
    }

    @Override
    public BaseOperacionResponse saveOrUpdate(CitaRequest request) {
        citaService.saveOrUpdate(request);
        return new BaseOperacionResponse(Constantes.SUCCESS, Constantes.MESSAGE_SAVE);
    }

    @Override
    public BaseOperacionResponse cancelarCita(Long citaId) {
        citaService.cancelar(citaId);
        return new BaseOperacionResponse(Constantes.SUCCESS, "Cita cancelada correctamente");
    }

    @Override
    public BaseOperacionResponse deleteCita(Long citaId) {
        citaService.delete(citaId);
        return new BaseOperacionResponse(Constantes.SUCCESS, Constantes.MESSAGE_DELETE);
    }

    @Override
    public CollectionResponse<CitaResponse> findCita(CitaFiltroRequest filtro) {
        List<CitaResponse> collection = new ArrayList<>();
        List<Cita> lista = citaService.find(filtro);
        lista.forEach(cita -> collection.add(toResponse(cita)));
        return new CollectionResponse<>(collection, filtro.getStart(),
                filtro.getLimit(), filtro.getTotalCount());
    }

    private CitaResponse toResponse(Cita cita) {
        CitaResponse response = new CitaResponse();
        response.setCitaId(cita.getCitaId());
        response.setInconveniente(cita.getInconveniente());
        response.setObservacion(cita.getObservacion());
        response.setFechaReserva(cita.getFechaReserva());
        response.setFechaConfirmacion(cita.getFechaConfirmacion());
        response.setFechaAtencion(cita.getFechaAtencion());
        response.setEstadoCodigo(cita.getEstadoCodigo());
        response.setHabilitado(cita.getHabilitado());
        if (cita.getPaciente() != null) {
            response.setPacienteId(cita.getPaciente().getPacienteId());
            response.setPacienteNombre(cita.getPaciente().getNombre());
            response.setPacienteApellido(cita.getPaciente().getApellido());
            response.setPacienteNumeroDocumento(
                    cita.getPaciente().getNumeroDocumento());
        }
        if (cita.getProgramacion() != null) {
            response.setProgramacionId(
                    cita.getProgramacion().getProgramacionId());
            if (cita.getProgramacion().getUsuarioServicio() != null) {
                response.setUsuarioServicioId(
                        cita.getProgramacion().getUsuarioServicio()
                                .getUsuarioServicioId());
                if (cita.getProgramacion().getUsuarioServicio().getUsuario() != null) {
                    response.setMedicoNombre(
                            cita.getProgramacion().getUsuarioServicio()
                                    .getUsuario().getNombre());
                    response.setMedicoApellido(
                            cita.getProgramacion().getUsuarioServicio()
                                    .getUsuario().getApellido());
                }
                if (cita.getProgramacion().getUsuarioServicio().getServicio() != null) {
                    response.setServicioNombre(
                            cita.getProgramacion().getUsuarioServicio()
                                    .getServicio().getNombre());
                }
            }
        }
        return response;
    }
}