package ms.clinica.gestion.app.cita.service;

import ms.clinica.gestion.app.cita.domain.Cita;
import ms.clinica.gestion.dto.model.cita.CitaFiltroRequest;
import ms.clinica.gestion.dto.model.cita.CitaRequest;
import java.util.List;

public interface CitaService {
    List<Cita> find(CitaFiltroRequest filtro);
    Cita get(Long citaId);
    void saveOrUpdate(CitaRequest request);
    void cancelar(Long citaId);
    void delete(Long citaId);
}