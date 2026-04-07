package ms.clinica.gestion.app.atencion.facade;

import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.atencion.AtencionFiltroRequest;
import ms.clinica.gestion.dto.model.atencion.AtencionRequest;
import ms.clinica.gestion.dto.model.atencion.AtencionResponse;
import ms.clinica.gestion.dto.model.atencion.EvidenciaRequest;
import ms.clinica.gestion.dto.model.atencion.RecetaRequest;

public interface AtencionFacade {
    AtencionResponse getByIdAtencion(Long atencionId);
    BaseOperacionResponse saveOrUpdate(AtencionRequest request);
    BaseOperacionResponse saveOrUpdateReceta(RecetaRequest request);
    BaseOperacionResponse saveOrUpdateEvidencia(EvidenciaRequest request);
    BaseOperacionResponse finalizarAtencion(Long atencionId);
    BaseOperacionResponse deleteAtencion(Long atencionId);
    BaseOperacionResponse deleteReceta(Long recetaId);
    BaseOperacionResponse deleteEvidencia(Long evidenciaId);
    CollectionResponse<AtencionResponse> findAtencion(AtencionFiltroRequest filtro);
}