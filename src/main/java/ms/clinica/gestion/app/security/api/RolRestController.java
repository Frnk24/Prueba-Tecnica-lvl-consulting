package ms.clinica.gestion.app.security.api;

import lombok.RequiredArgsConstructor;
import ms.clinica.gestion.app.security.facade.RolFacade;
import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.rol.RolFiltroRequest;
import ms.clinica.gestion.dto.model.rol.RolRequest;
import ms.clinica.gestion.dto.model.rol.RolResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rol")
@RequiredArgsConstructor
public class RolRestController {
    private final RolFacade rolFacade;

    @PostMapping("/find")
    public ResponseEntity<CollectionResponse<RolResponse>> findRol(@RequestBody RolFiltroRequest filtro){
        return ResponseEntity.ok(rolFacade.findRol(filtro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolResponse> getByIdRol(@PathVariable Long id) {
        return ResponseEntity.ok(rolFacade.getByIdRol(id));
     }

     @DeleteMapping("/{id}")
    public ResponseEntity<BaseOperacionResponse> deleteRol(@PathVariable Long id){
        return ResponseEntity.ok(rolFacade.deleteRol(id));
     }

     @PostMapping
    public ResponseEntity<BaseOperacionResponse> saveOrUpdateRol(@RequestBody RolRequest request){
        return ResponseEntity.ok(rolFacade.saveOrUpdate(request));
     }
}
