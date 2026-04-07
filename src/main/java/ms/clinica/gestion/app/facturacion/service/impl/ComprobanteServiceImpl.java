package ms.clinica.gestion.app.facturacion.service.impl;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.clinica.gestion.app.admision.domain.Paciente;
import ms.clinica.gestion.app.admision.repository.PacienteRepository;
import ms.clinica.gestion.app.cita.domain.Cita;
import ms.clinica.gestion.app.cita.repository.CitaRepository;
import ms.clinica.gestion.app.facturacion.domain.Comprobante;
import ms.clinica.gestion.app.facturacion.repository.ComprobanteRepository;
import ms.clinica.gestion.app.facturacion.service.ComprobanteService;
import ms.clinica.gestion.app.security.domain.Usuario;
import ms.clinica.gestion.app.security.repository.UsuarioRepository;
import ms.clinica.gestion.core.exception.BadRequestException;
import ms.clinica.gestion.core.exception.NotFoundException;
import ms.clinica.gestion.dto.model.comprobante.ComprobanteFiltroRequest;
import ms.clinica.gestion.dto.model.comprobante.ComprobanteRequest;
import ms.clinica.gestion.dto.util.Constantes;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComprobanteServiceImpl implements ComprobanteService {

    private final ComprobanteRepository comprobanteRepository;
    private final CitaRepository citaRepository;
    private final PacienteRepository pacienteRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Comprobante> find(ComprobanteFiltroRequest filtro) {
        return comprobanteRepository.findAll((Specification<Comprobante>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getPacienteId() != null && filtro.getPacienteId() > 0) {
                predicates.add(cb.equal(
                        root.get("paciente").get("pacienteId"),
                        filtro.getPacienteId()));
            }
            if (filtro.getCitaId() != null && filtro.getCitaId() > 0) {
                predicates.add(cb.equal(
                        root.get("cita").get("citaId"),
                        filtro.getCitaId()));
            }
            if (filtro.getEstadoCodigo() != null && !filtro.getEstadoCodigo().trim().isEmpty()) {
                predicates.add(cb.equal(root.get("estadoCodigo"),
                        filtro.getEstadoCodigo()));
            }
            if (filtro.getTipoCodigo() != null && !filtro.getTipoCodigo().trim().isEmpty()) {
                predicates.add(cb.equal(root.get("tipoCodigo"),
                        filtro.getTipoCodigo()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

    @Override
    @Transactional(readOnly = true)
    public Comprobante get(Long comprobanteId) {
        log.debug("Get Comprobante: {}", comprobanteId);
        return comprobanteRepository.findById(comprobanteId)
                .orElseThrow(() -> new NotFoundException(
                        "Comprobante no encontrado con id: " + comprobanteId));
    }

    @Override
    @Transactional
    public void saveOrUpdate(ComprobanteRequest request) {
        if (request.getComprobanteId() != null && request.getComprobanteId() > 0) {
            Comprobante comprobante = this.get(request.getComprobanteId());
            comprobante.setMetodoCodigo(request.getMetodoCodigo());
            comprobante.setTipoCodigo(request.getTipoCodigo());
            comprobante.setMonedaCodigo(request.getMonedaCodigo());
            comprobante.setNumeroCuota(request.getNumeroCuota());
            comprobante.setMontoPagado(request.getMontoPagado());
            comprobante.setTotal(request.getTotal());
            comprobante.setEstadoCodigo(request.getEstadoCodigo());
            comprobanteRepository.save(comprobante);
        } else {
            Cita cita = citaRepository.findById(request.getCitaId())
                    .orElseThrow(() -> new NotFoundException(
                            "Cita no encontrada con id: " + request.getCitaId()));

            if (!Constantes.EstadoCita.RESERVADO.equals(cita.getEstadoCodigo())) {
                throw new BadRequestException(
                        "Solo se puede registrar comprobante para citas en estado RESERVADO");
            }

            Paciente paciente = pacienteRepository.findById(request.getPacienteId())
                    .orElseThrow(() -> new NotFoundException(
                            "Paciente no encontrado con id: " + request.getPacienteId()));

            Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                    .orElseThrow(() -> new NotFoundException(
                            "Usuario no encontrado con id: " + request.getUsuarioId()));

            Comprobante comprobante = new Comprobante();
            comprobante.setCita(cita);
            comprobante.setPaciente(paciente);
            comprobante.setUsuario(usuario);
            comprobante.setMetodoCodigo(request.getMetodoCodigo());
            comprobante.setTipoCodigo(request.getTipoCodigo());
            comprobante.setMonedaCodigo(request.getMonedaCodigo());
            comprobante.setNumeroCuota(request.getNumeroCuota());
            comprobante.setMontoPagado(request.getMontoPagado());
            comprobante.setTotal(request.getTotal());

            if (request.getMontoPagado().compareTo(request.getTotal()) >= 0) {
                comprobante.setEstadoCodigo(Constantes.EstadoComprobante.PAGADO);
                cita.setEstadoCodigo(Constantes.EstadoCita.EN_ESPERA);
                citaRepository.save(cita);
            } else {
                comprobante.setEstadoCodigo(Constantes.EstadoComprobante.PENDIENTE);
            }

            comprobanteRepository.save(comprobante);
        }
    }

    @Override
    @Transactional
    public void anular(Long comprobanteId) {
        Comprobante comprobante = this.get(comprobanteId);
        if (Constantes.EstadoComprobante.ANULADO.equals(comprobante.getEstadoCodigo())) {
            throw new BadRequestException("El comprobante ya está anulado");
        }
        comprobante.setEstadoCodigo(Constantes.EstadoComprobante.ANULADO);
        comprobanteRepository.save(comprobante);
    }

    @Override
    @Transactional
    public void delete(Long comprobanteId) {
        Comprobante comprobante = this.get(comprobanteId);
        comprobante.setHabilitado((short) 0);
        comprobanteRepository.save(comprobante);
    }
}