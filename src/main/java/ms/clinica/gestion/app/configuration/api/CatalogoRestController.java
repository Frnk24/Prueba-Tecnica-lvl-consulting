package ms.clinica.gestion.app.configuration.api;

import lombok.RequiredArgsConstructor;
import ms.clinica.gestion.app.configuration.facade.CatalogoFacade;
import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.catalogo.CatalogoFiltroRequest;
import ms.clinica.gestion.dto.model.catalogo.CatalogoRequest;
import ms.clinica.gestion.dto.model.catalogo.CatalogoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/catalogo")
@RequiredArgsConstructor
public class CatalogoRestController {

    private final CatalogoFacade catalogoFacade;

    @PostMapping("/find")
    public ResponseEntity<CollectionResponse<CatalogoResponse>> findCatalogo(@RequestBody CatalogoFiltroRequest filtro){
        return ResponseEntity.ok(catalogoFacade.findCatalogo(filtro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatalogoResponse> getByIdCatalogo(@PathVariable Long id){
        return ResponseEntity.ok(catalogoFacade.getByIdCatalogo(id));
    }

    @PostMapping
    public ResponseEntity<BaseOperacionResponse> saveOrUpdateCatalogo(@RequestBody CatalogoRequest request){
        return ResponseEntity.ok(catalogoFacade.saveOrUpdateCatalogo(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseOperacionResponse> deleteCatalogo(@PathVariable Long id){
        return ResponseEntity.ok(catalogoFacade.deleteCatalogo(id));
    }
}
