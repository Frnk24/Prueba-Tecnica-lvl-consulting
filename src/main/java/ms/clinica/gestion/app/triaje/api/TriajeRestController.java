package ms.clinica.gestion.app.triaje.api;

import lombok.RequiredArgsConstructor;
import ms.clinica.gestion.app.triaje.facade.TriajeFacade;
import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.triaje.TriajeFiltroRequest;
import ms.clinica.gestion.dto.model.triaje.TriajeRequest;
import ms.clinica.gestion.dto.model.triaje.TriajeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/triaje")
@RequiredArgsConstructor
public class TriajeRestController {

    private final TriajeFacade triajeFacade;

    @PostMapping("/find")
    public ResponseEntity<CollectionResponse<TriajeResponse>> findTriaje(
            @RequestBody TriajeFiltroRequest filtro) {
        return ResponseEntity.ok(triajeFacade.findTriaje(filtro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TriajeResponse> getByIdTriaje(@PathVariable Long id) {
        return ResponseEntity.ok(triajeFacade.getByIdTriaje(id));
    }

    @PostMapping
    public ResponseEntity<BaseOperacionResponse> saveOrUpdate(
            @RequestBody TriajeRequest request) {
        return ResponseEntity.ok(triajeFacade.saveOrUpdate(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseOperacionResponse> deleteTriaje(@PathVariable Long id) {
        return ResponseEntity.ok(triajeFacade.deleteTriaje(id));
    }
}