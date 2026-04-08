package ms.clinica.gestion.app.reporte.api;

import lombok.RequiredArgsConstructor;
import ms.clinica.gestion.app.reporte.facade.ReporteFacade;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.reporte.HistorialAtencionResponse;
import ms.clinica.gestion.dto.model.reporte.HistorialTriajeResponse;
import ms.clinica.gestion.dto.model.reporte.HistorialVentaResponse;
import ms.clinica.gestion.dto.model.reporte.RankingEspecialidadResponse;
import ms.clinica.gestion.dto.model.reporte.RankingMedicoResponse;
import ms.clinica.gestion.dto.model.reporte.ReporteFiltroRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reporte")
@RequiredArgsConstructor
public class ReporteRestController {

    private final ReporteFacade reporteFacade;

    @GetMapping("/historial-atencion/paciente/{pacienteId}")
    public ResponseEntity<CollectionResponse<HistorialAtencionResponse>>
    historialAtencionPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(
                reporteFacade.findHistorialAtencionPaciente(pacienteId));
    }

    @PostMapping("/historial-atencion/general")
    public ResponseEntity<CollectionResponse<HistorialAtencionResponse>>
    historialAtencionGeneral(@RequestBody ReporteFiltroRequest filtro) {
        return ResponseEntity.ok(
                reporteFacade.findHistorialAtencionGeneral(filtro));
    }

    @PostMapping("/historial-ventas")
    public ResponseEntity<CollectionResponse<HistorialVentaResponse>>
    historialVentas(@RequestBody ReporteFiltroRequest filtro) {
        return ResponseEntity.ok(reporteFacade.findHistorialVentas(filtro));
    }

    @GetMapping("/historial-triaje/paciente/{pacienteId}")
    public ResponseEntity<CollectionResponse<HistorialTriajeResponse>>
    historialTriajePaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(
                reporteFacade.findHistorialTriajePaciente(pacienteId));
    }

    @PostMapping("/ranking-medicos")
    public ResponseEntity<CollectionResponse<RankingMedicoResponse>>
    rankingMedicos(@RequestBody ReporteFiltroRequest filtro) {
        return ResponseEntity.ok(reporteFacade.findRankingMedicos(filtro));
    }

    @PostMapping("/ranking-especialidades")
    public ResponseEntity<CollectionResponse<RankingEspecialidadResponse>>
    rankingEspecialidades(@RequestBody ReporteFiltroRequest filtro) {
        return ResponseEntity.ok(reporteFacade.findRankingEspecialidades(filtro));
    }
}