package ms.clinica.gestion.app.atencion.repository;

import ms.clinica.gestion.app.atencion.domain.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {

    @Query("SELECT r FROM Receta r WHERE r.atencion.atencionId = :atencionId AND r.habilitado = 1")
    List<Receta> findByAtencionId(@Param("atencionId") Long atencionId);
}