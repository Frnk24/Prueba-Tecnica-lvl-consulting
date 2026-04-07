package ms.clinica.gestion.app.programacion.facade.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.clinica.gestion.app.programacion.domain.Programacion;
import ms.clinica.gestion.app.programacion.facade.ProgramacionFacade;
import ms.clinica.gestion.app.programacion.service.ProgramacionService;
import ms.clinica.gestion.app.security.domain.UsuarioServicio;
import ms.clinica.gestion.app.security.service.UsuarioServicioService;
import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.programacion.ProgramacionFiltroRequest;
import ms.clinica.gestion.dto.model.programacion.ProgramacionRequest;
import ms.clinica.gestion.dto.model.programacion.ProgramacionResponse;
import ms.clinica.gestion.dto.util.Constantes;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProgramacionFacadeImpl implements ProgramacionFacade {

    private final ProgramacionService programacionService;
    private final ModelMapper modelMapper;

    @Override
    public ProgramacionResponse getByIdProgramacion(Long programacionId){
        Programacion programacion = programacionService.get(programacionId);
        return toResponse(programacionService.get(programacionId));
    }

    @Override
    public BaseOperacionResponse saveOrUpdate(ProgramacionRequest request){
        programacionService.saveOrUpdate(request);
        return new BaseOperacionResponse(Constantes.SUCCESS, Constantes.MESSAGE_SAVE);
    }

    @Override
    public BaseOperacionResponse delete(Long programacionId){
        programacionService.delete(programacionId);
        return new BaseOperacionResponse(Constantes.SUCCESS, Constantes.MESSAGE_DELETE);

    }

    @Override
    public CollectionResponse<ProgramacionResponse> findProgramacion(ProgramacionFiltroRequest filtro){
        List<ProgramacionResponse> collection = new ArrayList<>();
        List<Programacion> lista= programacionService.find(filtro);
        lista.forEach(programacion -> collection.add(toResponse(programacion)));
        return new CollectionResponse<>(collection, filtro.getStart(), filtro.getLimit(), filtro.getTotalCount());
    }

    private ProgramacionResponse toResponse(Programacion programacion) {
        ProgramacionResponse response = new ProgramacionResponse();
        response.setProgramacionId(programacion.getProgramacionId());
        response.setFecha(programacion.getFecha());
        response.setHoraInicio(programacion.getHoraInicio());
        response.setHoraFin(programacion.getHoraFin());
        response.setCupoTotal(programacion.getCupoTotal());
        response.setCupoOcupado(programacion.getCupoOcupado());
        response.setEstadoCodigo(programacion.getEstadoCodigo());
        response.setHabilitado(programacion.getHabilitado());
        if (programacion.getUsuarioServicio() != null) {
            response.setUsuarioServicioId(
                    programacion.getUsuarioServicio().getUsuarioServicioId());
            if (programacion.getUsuarioServicio().getUsuario() != null) {
                response.setUsuarioId(
                        programacion.getUsuarioServicio().getUsuario().getUsuarioId());
                response.setUsuarioNombre(
                        programacion.getUsuarioServicio().getUsuario().getNombre());
                response.setUsuarioApellido(
                        programacion.getUsuarioServicio().getUsuario().getApellido());
            }
            if (programacion.getUsuarioServicio().getServicio() != null) {
                response.setServicioId(
                        programacion.getUsuarioServicio().getServicio().getServicioId());
                response.setServicioNombre(
                        programacion.getUsuarioServicio().getServicio().getNombre());
            }
        }
        return response;
    }
}
