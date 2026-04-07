package ms.clinica.gestion.app.atencion.service;

import ms.clinica.gestion.app.atencion.domain.Atencion;
import ms.clinica.gestion.dto.model.atencion.AtencionFiltroRequest;
import ms.clinica.gestion.dto.model.atencion.AtencionRequest;
import ms.clinica.gestion.dto.model.atencion.EvidenciaRequest;
import ms.clinica.gestion.dto.model.atencion.RecetaRequest;
import java.util.List;

public interface AtencionService {
    List<Atencion> find(AtencionFiltroRequest filtro);
    Atencion get(Long atencionId);
    void saveOrUpdate(AtencionRequest request);
    void saveOrUpdateReceta(RecetaRequest request);
    void saveOrUpdateEvidencia(EvidenciaRequest request);
    void deleteReceta(Long recetaId);
    void deleteEvidencia(Long evidenciaId);
    void finalizar(Long atencionId);
    void delete(Long atencionId);
}