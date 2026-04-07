package ms.clinica.gestion.app.cita.api;

import lombok.RequiredArgsConstructor;
import ms.clinica.gestion.app.cita.facade.CitaFacade;
import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.cita.CitaFiltroRequest;
import ms.clinica.gestion.dto.model.cita.CitaRequest;
import ms.clinica.gestion.dto.model.cita.CitaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cita")
@RequiredArgsConstructor
public class CitaRestController {

    private final CitaFacade citaFacade;

    @PostMapping("/find")
    public ResponseEntity<CollectionResponse<CitaResponse>> findCita(
            @RequestBody CitaFiltroRequest filtro) {
        return ResponseEntity.ok(citaFacade.findCita(filtro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaResponse> getByIdCita(@PathVariable Long id) {
        return ResponseEntity.ok(citaFacade.getByIdCita(id));
    }

    @PostMapping
    public ResponseEntity<BaseOperacionResponse> saveOrUpdate(
            @RequestBody CitaRequest request) {
        return ResponseEntity.ok(citaFacade.saveOrUpdate(request));
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<BaseOperacionResponse> cancelarCita(@PathVariable Long id) {
        return ResponseEntity.ok(citaFacade.cancelarCita(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseOperacionResponse> deleteCita(@PathVariable Long id) {
        return ResponseEntity.ok(citaFacade.deleteCita(id));
    }
}