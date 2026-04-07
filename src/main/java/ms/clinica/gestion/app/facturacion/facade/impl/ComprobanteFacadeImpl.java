package ms.clinica.gestion.app.facturacion.facade.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.clinica.gestion.app.facturacion.domain.Comprobante;
import ms.clinica.gestion.app.facturacion.facade.ComprobanteFacade;
import ms.clinica.gestion.app.facturacion.service.ComprobanteService;
import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.comprobante.ComprobanteFiltroRequest;
import ms.clinica.gestion.dto.model.comprobante.ComprobanteRequest;
import ms.clinica.gestion.dto.model.comprobante.ComprobanteResponse;
import ms.clinica.gestion.dto.util.Constantes;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ComprobanteFacadeImpl implements ComprobanteFacade {

    private final ComprobanteService comprobanteService;

    @Override
    public ComprobanteResponse getByIdComprobante(Long comprobanteId) {
        return toResponse(comprobanteService.get(comprobanteId));
    }

    @Override
    public BaseOperacionResponse saveOrUpdate(ComprobanteRequest request) {
        comprobanteService.saveOrUpdate(request);
        return new BaseOperacionResponse(Constantes.SUCCESS, Constantes.MESSAGE_SAVE);
    }

    @Override
    public BaseOperacionResponse anularComprobante(Long comprobanteId) {
        comprobanteService.anular(comprobanteId);
        return new BaseOperacionResponse(Constantes.SUCCESS, "Comprobante anulado correctamente");
    }

    @Override
    public BaseOperacionResponse deleteComprobante(Long comprobanteId) {
        comprobanteService.delete(comprobanteId);
        return new BaseOperacionResponse(Constantes.SUCCESS, Constantes.MESSAGE_DELETE);
    }

    @Override
    public CollectionResponse<ComprobanteResponse> findComprobante(
            ComprobanteFiltroRequest filtro) {
        List<ComprobanteResponse> collection = new ArrayList<>();
        List<Comprobante> lista = comprobanteService.find(filtro);
        lista.forEach(comprobante -> collection.add(toResponse(comprobante)));
        return new CollectionResponse<>(collection, filtro.getStart(),
                filtro.getLimit(), filtro.getTotalCount());
    }

    private ComprobanteResponse toResponse(Comprobante comprobante) {
        ComprobanteResponse response = new ComprobanteResponse();
        response.setComprobanteId(comprobante.getComprobanteId());
        response.setMetodoCodigo(comprobante.getMetodoCodigo());
        response.setTipoCodigo(comprobante.getTipoCodigo());
        response.setMonedaCodigo(comprobante.getMonedaCodigo());
        response.setNumeroCuota(comprobante.getNumeroCuota());
        response.setMontoPagado(comprobante.getMontoPagado());
        response.setTotal(comprobante.getTotal());
        response.setFecha(comprobante.getFecha());
        response.setEstadoCodigo(comprobante.getEstadoCodigo());
        response.setHabilitado(comprobante.getHabilitado());
        if (comprobante.getPaciente() != null) {
            response.setPacienteId(comprobante.getPaciente().getPacienteId());
            response.setPacienteNombre(comprobante.getPaciente().getNombre());
            response.setPacienteApellido(comprobante.getPaciente().getApellido());
        }
        if (comprobante.getUsuario() != null) {
            response.setUsuarioId(comprobante.getUsuario().getUsuarioId());
            response.setUsuarioNombre(comprobante.getUsuario().getNombre());
        }
        if (comprobante.getCita() != null) {
            response.setCitaId(comprobante.getCita().getCitaId());
        }
        return response;
    }
}