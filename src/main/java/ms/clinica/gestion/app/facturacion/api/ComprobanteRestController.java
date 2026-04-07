package ms.clinica.gestion.app.facturacion.api;

import lombok.RequiredArgsConstructor;
import ms.clinica.gestion.app.facturacion.facade.ComprobanteFacade;
import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.comprobante.ComprobanteFiltroRequest;
import ms.clinica.gestion.dto.model.comprobante.ComprobanteRequest;
import ms.clinica.gestion.dto.model.comprobante.ComprobanteResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comprobante")
@RequiredArgsConstructor
public class ComprobanteRestController {

    private final ComprobanteFacade comprobanteFacade;

    @PostMapping("/find")
    public ResponseEntity<CollectionResponse<ComprobanteResponse>> findComprobante(
            @RequestBody ComprobanteFiltroRequest filtro) {
        return ResponseEntity.ok(comprobanteFacade.findComprobante(filtro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComprobanteResponse> getByIdComprobante(
            @PathVariable Long id) {
        return ResponseEntity.ok(comprobanteFacade.getByIdComprobante(id));
    }

    @PostMapping
    public ResponseEntity<BaseOperacionResponse> saveOrUpdate(
            @RequestBody ComprobanteRequest request) {
        return ResponseEntity.ok(comprobanteFacade.saveOrUpdate(request));
    }

    @PutMapping("/anular/{id}")
    public ResponseEntity<BaseOperacionResponse> anularComprobante(
            @PathVariable Long id) {
        return ResponseEntity.ok(comprobanteFacade.anularComprobante(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseOperacionResponse> deleteComprobante(
            @PathVariable Long id) {
        return ResponseEntity.ok(comprobanteFacade.deleteComprobante(id));
    }
}