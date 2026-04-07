package ms.clinica.gestion.app.admision.api;

import lombok.RequiredArgsConstructor;
import ms.clinica.gestion.app.admision.facade.PacienteFacade;
import ms.clinica.gestion.dto.model.BaseOperacionResponse;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.paciente.PacienteFiltroRequest;
import ms.clinica.gestion.dto.model.paciente.PacienteRequest;
import ms.clinica.gestion.dto.model.paciente.PacienteResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paciente")
@RequiredArgsConstructor
public class PacienteRestController {

    private final PacienteFacade pacienteFacade;

    @PostMapping("/find")
    public ResponseEntity<CollectionResponse<PacienteResponse>> findPaciente(@RequestBody PacienteFiltroRequest filtro) {
        return ResponseEntity.ok(pacienteFacade.findPaciente(filtro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> getByIdPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteFacade.getByIdPaciente(id));
    }

    @PostMapping
    public ResponseEntity<BaseOperacionResponse> saveOrUpdate(@RequestBody PacienteRequest request) {
        return ResponseEntity.ok(pacienteFacade.saveOrUpdate(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseOperacionResponse> deletePaciente(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteFacade.deletePaciente(id));
    }
}