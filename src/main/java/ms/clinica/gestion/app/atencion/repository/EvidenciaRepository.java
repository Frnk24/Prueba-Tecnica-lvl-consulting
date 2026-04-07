package ms.clinica.gestion.app.atencion.repository;

import ms.clinica.gestion.app.atencion.domain.Evidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EvidenciaRepository extends JpaRepository<Evidencia, Long> {

    @Query("SELECT e FROM Evidencia e WHERE e.atencion.atencionId = :atencionId AND e.habilitado = 1")
    List<Evidencia> findByAtencionId(@Param("atencionId") Long atencionId);
}