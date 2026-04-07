package ms.clinica.gestion.app.security.service;

import ms.clinica.gestion.app.security.domain.Rol;
import ms.clinica.gestion.dto.model.rol.RolFiltroRequest;
import ms.clinica.gestion.dto.model.rol.RolRequest;

import java.util.List;

public interface RolService {
    List<Rol> find(RolFiltroRequest filtro);
    Rol get(Long rolId);
    void delete(Long rolId);
    void saveOrUpdate(RolRequest request);
}
