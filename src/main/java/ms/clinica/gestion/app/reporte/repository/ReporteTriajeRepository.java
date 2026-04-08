package ms.clinica.gestion.app.reporte.repository;

import ms.clinica.gestion.app.triaje.domain.Triaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReporteTriajeRepository extends JpaRepository<Triaje, Long> {

    @Query("SELECT t FROM Triaje t " +
            "JOIN t.cita c " +
            "JOIN c.paciente p " +
            "WHERE p.pacienteId = :pacienteId " +
            "AND t.habilitado = 1 " +
            "ORDER BY t.fecha DESC")
    List<Triaje> findHistorialTriajePorPaciente(@Param("pacienteId") Long pacienteId);
}