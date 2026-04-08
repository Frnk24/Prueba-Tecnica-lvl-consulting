package ms.clinica.gestion.app.reporte.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.clinica.gestion.app.reporte.repository.ReporteAtencionRepository;
import ms.clinica.gestion.app.reporte.repository.ReporteRankingRepository;
import ms.clinica.gestion.app.reporte.repository.ReporteTriajeRepository;
import ms.clinica.gestion.app.reporte.repository.ReporteVentaRepository;
import ms.clinica.gestion.app.reporte.service.ReporteService;
import ms.clinica.gestion.dto.model.reporte.HistorialAtencionResponse;
import ms.clinica.gestion.dto.model.reporte.HistorialTriajeResponse;
import ms.clinica.gestion.dto.model.reporte.HistorialVentaResponse;
import ms.clinica.gestion.dto.model.reporte.RankingEspecialidadResponse;
import ms.clinica.gestion.dto.model.reporte.RankingMedicoResponse;
import ms.clinica.gestion.dto.model.reporte.ReporteFiltroRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReporteServiceImpl implements ReporteService {

    private final ReporteAtencionRepository atencionRepository;
    private final ReporteVentaRepository ventaRepository;
    private final ReporteTriajeRepository triajeRepository;
    private final ReporteRankingRepository rankingRepository;

    @Override
    @Transactional(readOnly = true)
    public List<HistorialAtencionResponse> historialAtencionPaciente(Long pacienteId) {
        return atencionRepository.findHistorialPorPaciente(pacienteId)
                .stream()
                .map(atencion -> {
                    HistorialAtencionResponse r = new HistorialAtencionResponse();
                    r.setAtencionId(atencion.getAtencionId());
                    r.setMotivoConsulta(atencion.getMotivoConsulta());
                    r.setDiagnostico(atencion.getDiagnostico());
                    r.setTratamiento(atencion.getTratamiento());
                    r.setObservacion(atencion.getObservacion());
                    r.setFechaAtencion(atencion.getCreado());
                    if (atencion.getCita() != null) {
                        r.setCitaId(atencion.getCita().getCitaId());
                        r.setEstadoCita(atencion.getCita().getEstadoCodigo());
                        if (atencion.getCita().getPaciente() != null) {
                            r.setPacienteId(atencion.getCita().getPaciente().getPacienteId());
                            r.setPacienteNombre(atencion.getCita().getPaciente().getNombre());
                            r.setPacienteApellido(atencion.getCita().getPaciente().getApellido());
                            r.setPacienteNumeroDocumento(atencion.getCita().getPaciente().getNumeroDocumento());
                        }
                        if (atencion.getCita().getProgramacion() != null &&
                                atencion.getCita().getProgramacion().getUsuarioServicio() != null) {
                            r.setFechaProgramacion(atencion.getCita().getProgramacion().getFecha());
                            r.setHoraInicio(atencion.getCita().getProgramacion().getHoraInicio());
                            if (atencion.getCita().getProgramacion().getUsuarioServicio().getUsuario() != null) {
                                r.setMedicoNombre(atencion.getCita().getProgramacion().getUsuarioServicio().getUsuario().getNombre());
                                r.setMedicoApellido(atencion.getCita().getProgramacion().getUsuarioServicio().getUsuario().getApellido());
                            }
                            if (atencion.getCita().getProgramacion().getUsuarioServicio().getServicio() != null) {
                                r.setServicioNombre(atencion.getCita().getProgramacion().getUsuarioServicio().getServicio().getNombre());
                            }
                        }
                    }
                    return r;
                }).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<HistorialAtencionResponse> historialAtencionGeneral(ReporteFiltroRequest filtro) {
        return atencionRepository.findHistorialGeneral(
                        filtro.getMedicoId(), filtro.getFechaInicio(), filtro.getFechaFin())
                .stream()
                .map(atencion -> {
                    HistorialAtencionResponse r = new HistorialAtencionResponse();
                    r.setAtencionId(atencion.getAtencionId());
                    r.setMotivoConsulta(atencion.getMotivoConsulta());
                    r.setDiagnostico(atencion.getDiagnostico());
                    r.setTratamiento(atencion.getTratamiento());
                    r.setObservacion(atencion.getObservacion());
                    r.setFechaAtencion(atencion.getCreado());
                    if (atencion.getCita() != null) {
                        r.setCitaId(atencion.getCita().getCitaId());
                        r.setEstadoCita(atencion.getCita().getEstadoCodigo());
                        if (atencion.getCita().getPaciente() != null) {
                            r.setPacienteId(atencion.getCita().getPaciente().getPacienteId());
                            r.setPacienteNombre(atencion.getCita().getPaciente().getNombre());
                            r.setPacienteApellido(atencion.getCita().getPaciente().getApellido());
                        }
                        if (atencion.getCita().getProgramacion() != null &&
                                atencion.getCita().getProgramacion().getUsuarioServicio() != null) {
                            r.setFechaProgramacion(atencion.getCita().getProgramacion().getFecha());
                            r.setHoraInicio(atencion.getCita().getProgramacion().getHoraInicio());
                            if (atencion.getCita().getProgramacion().getUsuarioServicio().getUsuario() != null) {
                                r.setMedicoNombre(atencion.getCita().getProgramacion().getUsuarioServicio().getUsuario().getNombre());
                                r.setMedicoApellido(atencion.getCita().getProgramacion().getUsuarioServicio().getUsuario().getApellido());
                            }
                            if (atencion.getCita().getProgramacion().getUsuarioServicio().getServicio() != null) {
                                r.setServicioNombre(atencion.getCita().getProgramacion().getUsuarioServicio().getServicio().getNombre());
                            }
                        }
                    }
                    return r;
                }).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<HistorialVentaResponse> historialVentas(ReporteFiltroRequest filtro) {
        return ventaRepository.findHistorialVentas(
                        filtro.getFechaInicio(), filtro.getFechaFin())
                .stream()
                .map(comprobante -> {
                    HistorialVentaResponse r = new HistorialVentaResponse();
                    r.setComprobanteId(comprobante.getComprobanteId());
                    r.setTipoCodigo(comprobante.getTipoCodigo());
                    r.setMetodoCodigo(comprobante.getMetodoCodigo());
                    r.setMonedaCodigo(comprobante.getMonedaCodigo());
                    r.setNumeroCuota(comprobante.getNumeroCuota());
                    r.setMontoPagado(comprobante.getMontoPagado());
                    r.setTotal(comprobante.getTotal());
                    r.setEstadoCodigo(comprobante.getEstadoCodigo());
                    r.setFecha(comprobante.getFecha());
                    if (comprobante.getPaciente() != null) {
                        r.setPacienteNombre(comprobante.getPaciente().getNombre());
                        r.setPacienteApellido(comprobante.getPaciente().getApellido());
                        r.setPacienteNumeroDocumento(comprobante.getPaciente().getNumeroDocumento());
                    }
                    if (comprobante.getUsuario() != null) {
                        r.setUsuarioNombre(comprobante.getUsuario().getNombre());
                    }
                    if (comprobante.getCita() != null) {
                        r.setCitaId(comprobante.getCita().getCitaId());
                    }
                    return r;
                }).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<HistorialTriajeResponse> historialTriajePaciente(Long pacienteId) {
        return triajeRepository.findHistorialTriajePorPaciente(pacienteId)
                .stream()
                .map(triaje -> {
                    HistorialTriajeResponse r = new HistorialTriajeResponse();
                    r.setTriajeId(triaje.getTriajeId());
                    r.setTalla(triaje.getTalla());
                    r.setPeso(triaje.getPeso());
                    r.setPresion(triaje.getPresion());
                    r.setTemperatura(triaje.getTemperatura());
                    r.setFrecuenciaCardiaca(triaje.getFrecuenciaCardiaca());
                    r.setSaturacion(triaje.getSaturacion());
                    r.setFechaTriaje(triaje.getFecha());
                    if (triaje.getCita() != null) {
                        r.setCitaId(triaje.getCita().getCitaId());
                        if (triaje.getCita().getPaciente() != null) {
                            r.setPacienteId(triaje.getCita().getPaciente().getPacienteId());
                            r.setPacienteNombre(triaje.getCita().getPaciente().getNombre());
                            r.setPacienteApellido(triaje.getCita().getPaciente().getApellido());
                        }
                        if (triaje.getCita().getProgramacion() != null &&
                                triaje.getCita().getProgramacion().getUsuarioServicio() != null) {
                            r.setFechaCita(triaje.getCita().getProgramacion().getFecha());
                            if (triaje.getCita().getProgramacion().getUsuarioServicio().getUsuario() != null) {
                                r.setMedicoNombre(triaje.getCita().getProgramacion().getUsuarioServicio().getUsuario().getNombre());
                            }
                            if (triaje.getCita().getProgramacion().getUsuarioServicio().getServicio() != null) {
                                r.setServicioNombre(triaje.getCita().getProgramacion().getUsuarioServicio().getServicio().getNombre());
                            }
                        }
                    }
                    return r;
                }).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RankingMedicoResponse> rankingMedicos(ReporteFiltroRequest filtro) {
        return rankingRepository.findRankingMedicos(
                filtro.getFechaInicio(), filtro.getFechaFin());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RankingEspecialidadResponse> rankingEspecialidades(ReporteFiltroRequest filtro) {
        return rankingRepository.findRankingEspecialidades(
                filtro.getFechaInicio(), filtro.getFechaFin());
    }
}