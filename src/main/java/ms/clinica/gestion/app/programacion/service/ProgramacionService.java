package ms.clinica.gestion.app.programacion.service;

import ms.clinica.gestion.app.programacion.domain.Programacion;
import ms.clinica.gestion.dto.model.programacion.ProgramacionFiltroRequest;
import ms.clinica.gestion.dto.model.programacion.ProgramacionRequest;

import java.util.List;

public interface ProgramacionService {
    List<Programacion> find(ProgramacionFiltroRequest filtro);
    Programacion get(Long programacionId);
    void saveOrUpdate(ProgramacionRequest request);
    void delete(Long programacionId);

    }
