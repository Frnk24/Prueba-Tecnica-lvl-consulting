package ms.clinica.gestion.app.triaje.service;

import ms.clinica.gestion.app.triaje.domain.Triaje;
import ms.clinica.gestion.dto.model.triaje.TriajeFiltroRequest;
import ms.clinica.gestion.dto.model.triaje.TriajeRequest;
import java.util.List;

public interface TriajeService {
    List<Triaje> find(TriajeFiltroRequest filtro);
    Triaje get(Long triajeId);
    void saveOrUpdate(TriajeRequest request);
    void delete(Long triajeId);
}