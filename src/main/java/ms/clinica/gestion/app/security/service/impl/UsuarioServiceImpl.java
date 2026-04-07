package ms.clinica.gestion.app.security.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.clinica.gestion.app.security.domain.Rol;
import ms.clinica.gestion.app.security.domain.Usuario;
import ms.clinica.gestion.app.security.repository.RolRepository;
import ms.clinica.gestion.app.security.repository.UsuarioRepository;
import ms.clinica.gestion.app.security.service.UsuarioService;
import ms.clinica.gestion.core.exception.BadRequestException;
import ms.clinica.gestion.core.exception.NotFoundException;
import ms.clinica.gestion.dto.model.usuario.UsuarioFiltroRequest;
import ms.clinica.gestion.dto.model.usuario.UsuarioRequest;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;
    private final RolRepository rolRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> find(UsuarioFiltroRequest filtro) {
        return usuarioRepository.findAll((Specification<Usuario>) (root, query, cb) -> {
            List<Predicate> predicates= new ArrayList<>();
                if (filtro.getUsuario() !=null && !filtro.getUsuario().trim().isEmpty()){
                    predicates.add(cb.equal(root.get("usuario"), filtro.getUsuario()));
                }
                if (filtro.getRolId() !=null && filtro.getRolId() >0){
                    predicates.add(cb.equal(root.get("rol").get("rolId"), filtro.getRolId()));
                }
                if (filtro.getCorreo() != null && !filtro.getCorreo().trim().isEmpty()){
                    predicates.add(cb.equal(root.get("correo"), filtro.getCorreo()));
                }
                if (filtro.getEspecialidadCodigo() !=null && !filtro.getEspecialidadCodigo().trim().isEmpty()){
                    predicates.add(cb.equal(root.get("especialidadCodigo"), filtro.getEspecialidadCodigo()));
                }
            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario get(Long usuarioId) {
        log.debug("Get Usuario: {}", usuarioId);
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(()-> new NotFoundException("Usuario no encontrado con id: " + usuarioId));
    }

    @Override
    @Transactional
    public void saveOrUpdate(UsuarioRequest request) {
        if (request.getUsuarioId() != null && request.getUsuarioId() >0){
            Usuario usuario= this.get(request.getUsuarioId());

            usuario.setNombre(request.getNombre());
            usuario.setApellido(request.getApellido());
            usuario.setCelular(request.getCelular());
            usuario.setDocumentoCodigo(request.getDocumentoCodigo());
            usuario.setNumeroDocumento(request.getNumeroDocumento());
            usuario.setEspecialidadCodigo(request.getEspecialidadCodigo());
            usuario.setUsuario(request.getUsuario());
            usuario.setContrasena(request.getContrasena());
            usuario.setCorreo(request.getCorreo());
            Rol rol=rolRepository.findById(request.getRolId()).orElseThrow(() -> new NotFoundException("Rol no encontrado con id: " + request.getRolId()));
            usuario.setRol(rol);
            usuarioRepository.save(usuario);
        }else {
            usuarioRepository.findByUsuario(request.getUsuario()).ifPresent(existente -> {
                throw new BadRequestException("Ya existe un usuario con el nombre: " + request.getUsuario());
            });
            usuarioRepository.findByCorreo(request.getCorreo()).ifPresent(existente -> {
                throw new BadRequestException("Ya existe un usuario con el correo: " + request.getCorreo());
            });
            Rol rol=rolRepository.findById(request.getRolId()).orElseThrow(() -> new NotFoundException("Rol no encontrado con id: " + request.getRolId()));

            Usuario usuario = modelMapper.map(request, Usuario.class);
            usuario.setRol(rol);
            usuarioRepository.save(usuario);
        }
    }

    @Override
    @Transactional
    public void delete(Long usuarioId) {
        Usuario usuario = this.get(usuarioId);
        usuario.setHabilitado((short) 0);
        usuarioRepository.save(usuario);
    }

}
