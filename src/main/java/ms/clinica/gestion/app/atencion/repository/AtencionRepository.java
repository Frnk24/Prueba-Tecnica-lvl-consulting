package ms.clinica.gestion.app.atencion.repository;

import ms.clinica.gestion.app.atencion.domain.Atencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AtencionRepository extends JpaRepository<Atencion, Long>,
        JpaSpecificationExecutor<Atencion> {

    @Query("SELECT a FROM Atencion a WHERE a.cita.citaId = :citaId")
    Optional<Atencion> findByCitaId(@Param("citaId") Long citaId);
}