package ms.clinica.gestion.app.admision.repository;

import ms.clinica.gestion.app.admision.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>,
        JpaSpecificationExecutor<Paciente> {

    Optional<Paciente> findByNumeroDocumento(String numeroDocumento);
    Optional<Paciente> findByUsuarioPaciente(String usuario);
    Optional<Paciente> findByCorreo(String correo);
}