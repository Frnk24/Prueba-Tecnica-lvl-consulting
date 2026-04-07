package ms.clinica.gestion.app.atencion.facade.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.clinica.gestion.app.atencion.domain.Atencion;
import ms.clinica.gestion.app.atencion.facade.AtencionFacade;
import ms.clinica.gestion.app.atencion.repository.EvidenciaRepository;
import ms.clinica.gestion.app.atencion.repository.RecetaRepository;
import ms.clinica.gestion.app.atencion.service.AtencionService;
import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.atencion.AtencionFiltroRequest;
import ms.clinica.gestion.dto.model.atencion.AtencionRequest;
import ms.clinica.gestion.dto.model.atencion.AtencionResponse;
import ms.clinica.gestion.dto.model.atencion.EvidenciaRequest;
import ms.clinica.gestion.dto.model.atencion.EvidenciaResponse;
import ms.clinica.gestion.dto.model.atencion.RecetaRequest;
import ms.clinica.gestion.dto.model.atencion.RecetaResponse;
import ms.clinica.gestion.dto.util.Constantes;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AtencionFacadeImpl implements AtencionFacade {

    private final AtencionService atencionService;
    private final RecetaRepository recetaRepository;
    private final EvidenciaRepository evidenciaRepository;

    @Override
    public AtencionResponse getByIdAtencion(Long atencionId) {
        return toResponse(atencionService.get(atencionId));
    }

    @Override
    public BaseOperacionResponse saveOrUpdate(AtencionRequest request) {
        atencionService.saveOrUpdate(request);
        return new BaseOperacionResponse(Constantes.SUCCESS, Constantes.MESSAGE_SAVE);
    }

    @Override
    public BaseOperacionResponse saveOrUpdateReceta(RecetaRequest request) {
        atencionService.saveOrUpdateReceta(request);
        return new BaseOperacionResponse(Constantes.SUCCESS, Constantes.MESSAGE_SAVE);
    }

    @Override
    public BaseOperacionResponse saveOrUpdateEvidencia(EvidenciaRequest request) {
        atencionService.saveOrUpdateEvidencia(request);
        return new BaseOperacionResponse(Constantes.SUCCESS, Constantes.MESSAGE_SAVE);
    }

    @Override
    public BaseOperacionResponse finalizarAtencion(Long atencionId) {
        atencionService.finalizar(atencionId);
        return new BaseOperacionResponse(Constantes.SUCCESS,
                "Atención finalizada correctamente");
    }

    @Override
    public BaseOperacionResponse deleteAtencion(Long atencionId) {
        atencionService.delete(atencionId);
        return new BaseOperacionResponse(Constantes.SUCCESS, Constantes.MESSAGE_DELETE);
    }

    @Override
    public BaseOperacionResponse deleteReceta(Long recetaId) {
        atencionService.deleteReceta(recetaId);
        return new BaseOperacionResponse(Constantes.SUCCESS, Constantes.MESSAGE_DELETE);
    }

    @Override
    public BaseOperacionResponse deleteEvidencia(Long evidenciaId) {
        atencionService.deleteEvidencia(evidenciaId);
        return new BaseOperacionResponse(Constantes.SUCCESS, Constantes.MESSAGE_DELETE);
    }

    @Override
    public CollectionResponse<AtencionResponse> findAtencion(AtencionFiltroRequest filtro) {
        List<AtencionResponse> collection = new ArrayList<>();
        List<Atencion> lista = atencionService.find(filtro);
        lista.forEach(atencion -> collection.add(toResponse(atencion)));
        return new CollectionResponse<>(collection, filtro.getStart(),
                filtro.getLimit(), filtro.getTotalCount());
    }

    private AtencionResponse toResponse(Atencion atencion) {
        AtencionResponse response = new AtencionResponse();
        response.setAtencionId(atencion.getAtencionId());
        response.setMotivoConsulta(atencion.getMotivoConsulta());
        response.setDiagnostico(atencion.getDiagnostico());
        response.setTratamiento(atencion.getTratamiento());
        response.setObservacion(atencion.getObservacion());
        response.setCreado(atencion.getCreado());
        response.setHabilitado(atencion.getHabilitado());

        if (atencion.getCita() != null) {
            response.setCitaId(atencion.getCita().getCitaId());
            if (atencion.getCita().getPaciente() != null) {
                response.setPacienteNombre(
                        atencion.getCita().getPaciente().getNombre());
                response.setPacienteApellido(
                        atencion.getCita().getPaciente().getApellido());
            }
            if (atencion.getCita().getProgramacion() != null &&
                    atencion.getCita().getProgramacion().getUsuarioServicio() != null) {
                if (atencion.getCita().getProgramacion()
                        .getUsuarioServicio().getUsuario() != null) {
                    response.setMedicoNombre(atencion.getCita().getProgramacion()
                            .getUsuarioServicio().getUsuario().getNombre());
                    response.setMedicoApellido(atencion.getCita().getProgramacion()
                            .getUsuarioServicio().getUsuario().getApellido());
                }
                if (atencion.getCita().getProgramacion()
                        .getUsuarioServicio().getServicio() != null) {
                    response.setServicioNombre(atencion.getCita().getProgramacion()
                            .getUsuarioServicio().getServicio().getNombre());
                }
            }
        }

        List<RecetaResponse> recetas = new ArrayList<>();
        recetaRepository.findByAtencionId(atencion.getAtencionId())
                .forEach(receta -> {
                    RecetaResponse rr = new RecetaResponse();
                    rr.setRecetaId(receta.getRecetaId());
                    rr.setAtencionId(atencion.getAtencionId());
                    rr.setMedicamento(receta.getMedicamento());
                    rr.setDosis(receta.getDosis());
                    rr.setFrecuencia(receta.getFrecuencia());
                    rr.setDuracion(receta.getDuracion());
                    rr.setHabilitado(receta.getHabilitado());
                    recetas.add(rr);
                });
        response.setRecetas(recetas);

        List<EvidenciaResponse> evidencias = new ArrayList<>();
        evidenciaRepository.findByAtencionId(atencion.getAtencionId())
                .forEach(evidencia -> {
                    EvidenciaResponse er = new EvidenciaResponse();
                    er.setEvidenciaId(evidencia.getEvidenciaId());
                    er.setAtencionId(atencion.getAtencionId());
                    er.setUrl(evidencia.getUrl());
                    er.setDescripcion(evidencia.getDescripcion());
                    er.setHabilitado(evidencia.getHabilitado());
                    evidencias.add(er);
                });
        response.setEvidencias(evidencias);

        return response;
    }
}