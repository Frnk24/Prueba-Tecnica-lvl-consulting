package ms.clinica.gestion.app.atencion.api;

import lombok.RequiredArgsConstructor;
import ms.clinica.gestion.app.atencion.facade.AtencionFacade;
import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.atencion.AtencionFiltroRequest;
import ms.clinica.gestion.dto.model.atencion.AtencionRequest;
import ms.clinica.gestion.dto.model.atencion.AtencionResponse;
import ms.clinica.gestion.dto.model.atencion.EvidenciaRequest;
import ms.clinica.gestion.dto.model.atencion.RecetaRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/atencion")
@RequiredArgsConstructor
public class AtencionRestController {

    private final AtencionFacade atencionFacade;

    @PostMapping("/find")
    public ResponseEntity<CollectionResponse<AtencionResponse>> findAtencion(
            @RequestBody AtencionFiltroRequest filtro) {
        return ResponseEntity.ok(atencionFacade.findAtencion(filtro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtencionResponse> getByIdAtencion(@PathVariable Long id) {
        return ResponseEntity.ok(atencionFacade.getByIdAtencion(id));
    }

    @PostMapping
    public ResponseEntity<BaseOperacionResponse> saveOrUpdate(
            @RequestBody AtencionRequest request) {
        return ResponseEntity.ok(atencionFacade.saveOrUpdate(request));
    }

    @PostMapping("/receta")
    public ResponseEntity<BaseOperacionResponse> saveOrUpdateReceta(
            @RequestBody RecetaRequest request) {
        return ResponseEntity.ok(atencionFacade.saveOrUpdateReceta(request));
    }

    @PostMapping("/evidencia")
    public ResponseEntity<BaseOperacionResponse> saveOrUpdateEvidencia(
            @RequestBody EvidenciaRequest request) {
        return ResponseEntity.ok(atencionFacade.saveOrUpdateEvidencia(request));
    }

    @PutMapping("/finalizar/{id}")
    public ResponseEntity<BaseOperacionResponse> finalizarAtencion(
            @PathVariable Long id) {
        return ResponseEntity.ok(atencionFacade.finalizarAtencion(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseOperacionResponse> deleteAtencion(@PathVariable Long id) {
        return ResponseEntity.ok(atencionFacade.deleteAtencion(id));
    }

    @DeleteMapping("/receta/{id}")
    public ResponseEntity<BaseOperacionResponse> deleteReceta(@PathVariable Long id) {
        return ResponseEntity.ok(atencionFacade.deleteReceta(id));
    }

    @DeleteMapping("/evidencia/{id}")
    public ResponseEntity<BaseOperacionResponse> deleteEvidencia(@PathVariable Long id) {
        return ResponseEntity.ok(atencionFacade.deleteEvidencia(id));
    }
}