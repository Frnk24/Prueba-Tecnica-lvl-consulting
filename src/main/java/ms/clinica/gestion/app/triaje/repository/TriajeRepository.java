package ms.clinica.gestion.app.triaje.repository;

import ms.clinica.gestion.app.triaje.domain.Triaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TriajeRepository extends JpaRepository<Triaje, Long>,
        JpaSpecificationExecutor<Triaje> {

    @Query("SELECT t FROM Triaje t WHERE t.cita.citaId = :citaId")
    Optional<Triaje> findByCitaId(@Param("citaId") Long citaId);
}