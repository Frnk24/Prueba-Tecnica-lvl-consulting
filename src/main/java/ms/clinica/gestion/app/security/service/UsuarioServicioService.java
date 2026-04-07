package ms.clinica.gestion.app.security.service;

import ms.clinica.gestion.app.security.domain.UsuarioServicio;
import ms.clinica.gestion.dto.model.usuarioservicio.UsuarioServicioFiltroRequest;
import ms.clinica.gestion.dto.model.usuarioservicio.UsuarioServicioRequest;
import java.util.List;

public interface UsuarioServicioService {
    List<UsuarioServicio> find(UsuarioServicioFiltroRequest filtro);
    UsuarioServicio get(Long usuarioServicioId);
    void saveOrUpdate(UsuarioServicioRequest request);
    void delete(Long usuarioServicioId);
}