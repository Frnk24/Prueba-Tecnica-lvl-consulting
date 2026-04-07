package ms.clinica.gestion.app.admision.service.impl;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.clinica.gestion.app.admision.domain.Paciente;
import ms.clinica.gestion.app.admision.repository.PacienteRepository;
import ms.clinica.gestion.app.admision.service.PacienteService;
import ms.clinica.gestion.app.security.domain.Usuario;
import ms.clinica.gestion.app.security.repository.UsuarioRepository;
import ms.clinica.gestion.core.exception.BadRequestException;
import ms.clinica.gestion.core.exception.NotFoundException;
import ms.clinica.gestion.dto.model.paciente.PacienteFiltroRequest;
import ms.clinica.gestion.dto.model.paciente.PacienteRequest;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> find(PacienteFiltroRequest filtro) {
        return pacienteRepository.findAll((Specification<Paciente>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getNombre() != null && !filtro.getNombre().trim().isEmpty()) {
                predicates.add(cb.like(cb.upper(root.get("nombre")),
                        "%" + filtro.getNombre().toUpperCase() + "%"));
            }
            if (filtro.getApellido() != null && !filtro.getApellido().trim().isEmpty()) {
                predicates.add(cb.like(cb.upper(root.get("apellido")),
                        "%" + filtro.getApellido().toUpperCase() + "%"));
            }
            if (filtro.getNumeroDocumento() != null && !filtro.getNumeroDocumento().trim().isEmpty()) {
                predicates.add(cb.equal(root.get("numeroDocumento"),
                        filtro.getNumeroDocumento()));
            }
            if (filtro.getCorreo() != null && !filtro.getCorreo().trim().isEmpty()) {
                predicates.add(cb.equal(root.get("correo"), filtro.getCorreo()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

    @Override
    @Transactional(readOnly = true)
    public Paciente get(Long pacienteId) {
        log.debug("Get Paciente: {}", pacienteId);
        return pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new NotFoundException(
                        "Paciente no encontrado con id: " + pacienteId));
    }

    @Override
    @Transactional
    public void saveOrUpdate(PacienteRequest request) {
        if (request.getPacienteId() != null && request.getPacienteId() > 0) {
            Paciente paciente = this.get(request.getPacienteId());
            paciente.setNombre(request.getNombre());
            paciente.setApellido(request.getApellido());
            paciente.setCelular(request.getCelular());
            paciente.setDocumentoCodigo(request.getDocumentoCodigo());
            paciente.setCorreo(request.getCorreo());
            if (request.getUsuarioId() != null) {
                Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                        .orElseThrow(() -> new NotFoundException(
                                "Usuario no encontrado con id: " + request.getUsuarioId()));
                paciente.setUsuario(usuario);
            }
            pacienteRepository.save(paciente);
        } else {
            pacienteRepository.findByNumeroDocumento(request.getNumeroDocumento())
                    .ifPresent(existente -> {
                        throw new BadRequestException("Ya existe un paciente con el documento: "
                                + request.getNumeroDocumento());
                    });
            Paciente paciente = modelMapper.map(request, Paciente.class);
            if (request.getUsuarioId() != null) {
                Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                        .orElseThrow(() -> new NotFoundException(
                                "Usuario no encontrado con id: " + request.getUsuarioId()));
                paciente.setUsuario(usuario);
            }
            pacienteRepository.save(paciente);
        }
    }

    @Override
    @Transactional
    public void delete(Long pacienteId) {
        Paciente paciente = this.get(pacienteId);
        paciente.setHabilitado((short) 0);
        pacienteRepository.save(paciente);
    }
}