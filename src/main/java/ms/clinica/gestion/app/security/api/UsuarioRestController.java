package ms.clinica.gestion.app.security.api;

import lombok.RequiredArgsConstructor;
import ms.clinica.gestion.app.security.facade.UsuarioFacade;
import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.usuario.UsuarioFiltroRequest;
import ms.clinica.gestion.dto.model.usuario.UsuarioRequest;
import ms.clinica.gestion.dto.model.usuario.UsuarioResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioRestController {
    private final UsuarioFacade usuarioFacade;

    @PostMapping("/find")
    public ResponseEntity<CollectionResponse<UsuarioResponse>> findUsuario(@RequestBody UsuarioFiltroRequest filtro){
        return ResponseEntity.ok(usuarioFacade.findUsuario(filtro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> getByIdUsuario(@PathVariable Long id){
        return ResponseEntity.ok(usuarioFacade.getByIdUsuario(id));
    }

    @PostMapping
    public ResponseEntity<BaseOperacionResponse> saveOrUpdate(@RequestBody UsuarioRequest request){
        return ResponseEntity.ok(usuarioFacade.saveOrUpdate(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseOperacionResponse> deleteUsuario(@PathVariable Long id){
        return ResponseEntity.ok(usuarioFacade.deleteUsuario(id));
    }
}
