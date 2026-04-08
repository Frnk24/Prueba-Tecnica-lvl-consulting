package ms.clinica.gestion.app.reporte.service;

import ms.clinica.gestion.dto.model.reporte.HistorialAtencionResponse;
import ms.clinica.gestion.dto.model.reporte.HistorialTriajeResponse;
import ms.clinica.gestion.dto.model.reporte.HistorialVentaResponse;
import ms.clinica.gestion.dto.model.reporte.RankingEspecialidadResponse;
import ms.clinica.gestion.dto.model.reporte.RankingMedicoResponse;
import ms.clinica.gestion.dto.model.reporte.ReporteFiltroRequest;
import java.util.List;

public interface ReporteService {
    List<HistorialAtencionResponse> historialAtencionPaciente(Long pacienteId);
    List<HistorialAtencionResponse> historialAtencionGeneral(ReporteFiltroRequest filtro);
    List<HistorialVentaResponse> historialVentas(ReporteFiltroRequest filtro);
    List<HistorialTriajeResponse> historialTriajePaciente(Long pacienteId);
    List<RankingMedicoResponse> rankingMedicos(ReporteFiltroRequest filtro);
    List<RankingEspecialidadResponse> rankingEspecialidades(ReporteFiltroRequest filtro);
}