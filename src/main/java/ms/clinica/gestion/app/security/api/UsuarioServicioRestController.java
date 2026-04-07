package ms.clinica.gestion.app.security.api;

import lombok.RequiredArgsConstructor;
import ms.clinica.gestion.app.security.facade.UsuarioServicioFacade;
import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.usuarioservicio.UsuarioServicioFiltroRequest;
import ms.clinica.gestion.dto.model.usuarioservicio.UsuarioServicioRequest;
import ms.clinica.gestion.dto.model.usuarioservicio.UsuarioServicioResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario-servicio")
@RequiredArgsConstructor
public class UsuarioServicioRestController {

    private final UsuarioServicioFacade usuarioServicioFacade;

    @PostMapping("/find")
    public ResponseEntity<CollectionResponse<UsuarioServicioResponse>> findUsuarioServicio(
            @RequestBody UsuarioServicioFiltroRequest filtro) {
        return ResponseEntity.ok(usuarioServicioFacade.findUsuarioServicio(filtro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioServicioResponse> getByIdUsuarioServicio(
            @PathVariable Long id) {
        return ResponseEntity.ok(usuarioServicioFacade.getByIdUsuarioServicio(id));
    }

    @PostMapping
    public ResponseEntity<BaseOperacionResponse> saveOrUpdate(
            @RequestBody UsuarioServicioRequest request) {
        return ResponseEntity.ok(usuarioServicioFacade.saveOrUpdate(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseOperacionResponse> deleteUsuarioServicio(
            @PathVariable Long id) {
        return ResponseEntity.ok(usuarioServicioFacade.deleteUsuarioServicio(id));
    }
}