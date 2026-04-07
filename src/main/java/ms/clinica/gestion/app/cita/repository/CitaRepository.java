package ms.clinica.gestion.app.cita.repository;

import ms.clinica.gestion.app.cita.domain.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long>,
        JpaSpecificationExecutor<Cita> {

    @Query("SELECT c FROM Cita c WHERE c.programacion.programacionId = :programacionId AND c.habilitado = 1")
    List<Cita> findByProgramacionId(@Param("programacionId") Long programacionId);
}