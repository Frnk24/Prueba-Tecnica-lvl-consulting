package ms.clinica.gestion.app.facturacion.facade;

import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.comprobante.ComprobanteFiltroRequest;
import ms.clinica.gestion.dto.model.comprobante.ComprobanteRequest;
import ms.clinica.gestion.dto.model.comprobante.ComprobanteResponse;

public interface ComprobanteFacade {
    ComprobanteResponse getByIdComprobante(Long comprobanteId);
    BaseOperacionResponse saveOrUpdate(ComprobanteRequest request);
    BaseOperacionResponse anularComprobante(Long comprobanteId);
    BaseOperacionResponse deleteComprobante(Long comprobanteId);
    CollectionResponse<ComprobanteResponse> findComprobante(ComprobanteFiltroRequest filtro);
}