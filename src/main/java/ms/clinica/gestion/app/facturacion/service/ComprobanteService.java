package ms.clinica.gestion.app.facturacion.service;

import ms.clinica.gestion.app.facturacion.domain.Comprobante;
import ms.clinica.gestion.dto.model.comprobante.ComprobanteFiltroRequest;
import ms.clinica.gestion.dto.model.comprobante.ComprobanteRequest;
import java.util.List;

public interface ComprobanteService {
    List<Comprobante> find(ComprobanteFiltroRequest filtro);
    Comprobante get(Long comprobanteId);
    void saveOrUpdate(ComprobanteRequest request);
    void anular(Long comprobanteId);
    void delete(Long comprobanteId);
}