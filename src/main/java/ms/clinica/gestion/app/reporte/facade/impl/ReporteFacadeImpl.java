package ms.clinica.gestion.app.reporte.facade.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.clinica.gestion.app.reporte.facade.ReporteFacade;
import ms.clinica.gestion.app.reporte.service.ReporteService;
import ms.clinica.gestion.dto.model.CollectionResponse;
import ms.clinica.gestion.dto.model.reporte.HistorialAtencionResponse;
import ms.clinica.gestion.dto.model.reporte.HistorialTriajeResponse;
import ms.clinica.gestion.dto.model.reporte.HistorialVentaResponse;
import ms.clinica.gestion.dto.model.reporte.RankingEspecialidadResponse;
import ms.clinica.gestion.dto.model.reporte.RankingMedicoResponse;
import ms.clinica.gestion.dto.model.reporte.ReporteFiltroRequest;
import org.springframework.stereotype.Component;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReporteFacadeImpl implements ReporteFacade {

    private final ReporteService reporteService;

    @Override
    public CollectionResponse<HistorialAtencionResponse> findHistorialAtencionPaciente(
            Long pacienteId) {
        List<HistorialAtencionResponse> lista =
                reporteService.historialAtencionPaciente(pacienteId);
        return new CollectionResponse<>(lista);
    }

    @Override
    public CollectionResponse<HistorialAtencionResponse> findHistorialAtencionGeneral(
            ReporteFiltroRequest filtro) {
        List<HistorialAtencionResponse> lista =
                reporteService.historialAtencionGeneral(filtro);
        return new CollectionResponse<>(lista);
    }

    @Override
    public CollectionResponse<HistorialVentaResponse> findHistorialVentas(
            ReporteFiltroRequest filtro) {
        List<HistorialVentaResponse> lista =
                reporteService.historialVentas(filtro);
        return new CollectionResponse<>(lista);
    }

    @Override
    public CollectionResponse<HistorialTriajeResponse> findHistorialTriajePaciente(
            Long pacienteId) {
        List<HistorialTriajeResponse> lista =
                reporteService.historialTriajePaciente(pacienteId);
        return new CollectionResponse<>(lista);
    }

    @Override
    public CollectionResponse<RankingMedicoResponse> findRankingMedicos(
            ReporteFiltroRequest filtro) {
        List<RankingMedicoResponse> lista =
                reporteService.rankingMedicos(filtro);
        return new CollectionResponse<>(lista);
    }

    @Override
    public CollectionResponse<RankingEspecialidadResponse> findRankingEspecialidades(
            ReporteFiltroRequest filtro) {
        List<RankingEspecialidadResponse> lista =
                reporteService.rankingEspecialidades(filtro);
        return new CollectionResponse<>(lista);
    }
}