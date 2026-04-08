package ms.clinica.gestion.app.reporte.facade;

import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.reporte.HistorialAtencionResponse;
import ms.clinica.gestion.dto.model.reporte.HistorialTriajeResponse;
import ms.clinica.gestion.dto.model.reporte.HistorialVentaResponse;
import ms.clinica.gestion.dto.model.reporte.RankingEspecialidadResponse;
import ms.clinica.gestion.dto.model.reporte.RankingMedicoResponse;
import ms.clinica.gestion.dto.model.reporte.ReporteFiltroRequest;

public interface ReporteFacade {
    CollectionResponse<HistorialAtencionResponse> findHistorialAtencionPaciente(Long pacienteId);
    CollectionResponse<HistorialAtencionResponse> findHistorialAtencionGeneral(ReporteFiltroRequest filtro);
    CollectionResponse<HistorialVentaResponse> findHistorialVentas(ReporteFiltroRequest filtro);
    CollectionResponse<HistorialTriajeResponse> findHistorialTriajePaciente(Long pacienteId);
    CollectionResponse<RankingMedicoResponse> findRankingMedicos(ReporteFiltroRequest filtro);
    CollectionResponse<RankingEspecialidadResponse> findRankingEspecialidades(ReporteFiltroRequest filtro);
}