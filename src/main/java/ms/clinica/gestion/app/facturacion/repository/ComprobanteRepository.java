package ms.clinica.gestion.app.facturacion.repository;

import ms.clinica.gestion.app.facturacion.domain.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ComprobanteRepository extends JpaRepository<Comprobante, Long>,
        JpaSpecificationExecutor<Comprobante> {

    @Query("SELECT c FROM Comprobante c WHERE c.cita.citaId = :citaId AND c.habilitado = 1")
    Optional<Comprobante> findByCitaId(@Param("citaId") Long citaId);
}