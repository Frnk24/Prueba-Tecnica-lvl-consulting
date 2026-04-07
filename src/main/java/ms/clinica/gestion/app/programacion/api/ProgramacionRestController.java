package ms.clinica.gestion.app.programacion.api;

import lombok.RequiredArgsConstructor;
import ms.clinica.gestion.app.programacion.facade.ProgramacionFacade;
import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.programacion.ProgramacionFiltroRequest;
import ms.clinica.gestion.dto.model.programacion.ProgramacionRequest;
import ms.clinica.gestion.dto.model.programacion.ProgramacionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/programacion")
@RequiredArgsConstructor
public class ProgramacionRestController {
    private final ProgramacionFacade programacionFacade;

    @PostMapping("/find")
    public ResponseEntity<CollectionResponse<ProgramacionResponse>> findProgramacion(@RequestBody ProgramacionFiltroRequest filtro){
        return ResponseEntity.ok(programacionFacade.findProgramacion(filtro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramacionResponse> getByIdProgramacion(@PathVariable Long id){
        return ResponseEntity.ok(programacionFacade.getByIdProgramacion(id));
    }

    @PostMapping
    public ResponseEntity<BaseOperacionResponse> saveOrUpdate(@RequestBody ProgramacionRequest request){
        return ResponseEntity.ok(programacionFacade.saveOrUpdate(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseOperacionResponse> deleteProgramacion(@PathVariable Long id){
        return ResponseEntity.ok(programacionFacade.delete(id));
    }
}
