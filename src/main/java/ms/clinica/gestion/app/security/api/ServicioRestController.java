package ms.clinica.gestion.app.security.api;

import lombok.RequiredArgsConstructor;
import ms.clinica.gestion.app.security.facade.ServicioFacade;
import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.servicio.ServicioFiltroRequest;
import ms.clinica.gestion.dto.model.servicio.ServicioRequest;
import ms.clinica.gestion.dto.model.servicio.ServicioResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/servicio")
@RequiredArgsConstructor
public class ServicioRestController {
    private final ServicioFacade servicioFacade;

    @PostMapping("/find")
    public ResponseEntity<CollectionResponse<ServicioResponse>> findServicio(@RequestBody ServicioFiltroRequest filtro){
        return ResponseEntity.ok(servicioFacade.findServicio(filtro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioResponse> getByIdServicio(@PathVariable Long id){
        return ResponseEntity.ok(servicioFacade.getByIdServicio(id));
    }

    @PostMapping
    public ResponseEntity<BaseOperacionResponse> saveOrUpdate(@RequestBody ServicioRequest request){
        return ResponseEntity.ok(servicioFacade.saveOrUpdate(request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BaseOperacionResponse> deleteServicio(@PathVariable Long id){
        return ResponseEntity.ok(servicioFacade.deleteServicio(id));

    }
}
