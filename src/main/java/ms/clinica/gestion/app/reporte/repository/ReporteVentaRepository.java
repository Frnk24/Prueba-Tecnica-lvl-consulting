package ms.clinica.gestion.app.reporte.repository;

import ms.clinica.gestion.app.facturacion.domain.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReporteVentaRepository extends JpaRepository<Comprobante, Long> {

    @Query("SELECT c FROM Comprobante c " +
            "WHERE (CAST(:fechaInicio AS date) IS NULL OR CAST(c.fecha AS date) >= :fechaInicio) " +
            "AND (CAST(:fechaFin AS date) IS NULL OR CAST(c.fecha AS date) <= :fechaFin) " +
            "AND c.habilitado = 1 " +
            "ORDER BY c.fecha DESC")
    List<Comprobante> findHistorialVentas(
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin);
}