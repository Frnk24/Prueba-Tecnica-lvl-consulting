package ms.clinica.gestion.app.security.service;

import ms.clinica.gestion.app.security.domain.Servicio;
import ms.clinica.gestion.dto.model.servicio.ServicioFiltroRequest;
import ms.clinica.gestion.dto.model.servicio.ServicioRequest;

import java.util.List;

public interface ServicioService {
    List<Servicio> find(ServicioFiltroRequest filtro);
    Servicio get(Long servicioId);
    void delete(Long servicioId);
    void saveOrUpdate(ServicioRequest request);
}
