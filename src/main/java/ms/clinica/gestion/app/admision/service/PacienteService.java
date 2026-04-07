package ms.clinica.gestion.app.admision.service;

import ms.clinica.gestion.app.admision.domain.Paciente;
import ms.clinica.gestion.dto.model.paciente.PacienteFiltroRequest;
import ms.clinica.gestion.dto.model.paciente.PacienteRequest;
import java.util.List;

public interface PacienteService {
    List<Paciente> find(PacienteFiltroRequest filtro);
    Paciente get(Long pacienteId);
    void saveOrUpdate(PacienteRequest request);
    void delete(Long pacienteId);
}