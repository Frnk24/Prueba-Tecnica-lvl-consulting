package ms.clinica.gestion.app.security.service;

import ms.clinica.gestion.app.security.domain.Usuario;
import ms.clinica.gestion.dto.model.usuario.UsuarioFiltroRequest;
import ms.clinica.gestion.dto.model.usuario.UsuarioRequest;

import java.util.List;

public interface UsuarioService {
    List<Usuario> find(UsuarioFiltroRequest filtro);
    Usuario get(Long usuarioId);
    void delete(Long usuarioId);
    void saveOrUpdate(UsuarioRequest request);
}
