package ms.clinica.gestion.app.reporte.repository;

import ms.clinica.gestion.app.atencion.domain.Atencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReporteAtencionRepository extends JpaRepository<Atencion, Long> {

    @Query("SELECT a FROM Atencion a " +
            "JOIN a.cita c " +
            "JOIN c.paciente p " +
            "WHERE p.pacienteId = :pacienteId " +
            "AND a.habilitado = 1 " +
            "ORDER BY a.creado DESC")
    List<Atencion> findHistorialPorPaciente(@Param("pacienteId") Long pacienteId);

    @Query("SELECT a FROM Atencion a " +
            "JOIN a.cita c " +
            "JOIN c.programacion prog " +
            "JOIN prog.usuarioServicio us " +
            "WHERE (CAST(:medicoId AS long) IS NULL OR us.usuario.usuarioId = :medicoId) " +
            "AND (CAST(:fechaInicio AS date) IS NULL OR prog.fecha >= :fechaInicio) " +
            "AND (CAST(:fechaFin AS date) IS NULL OR prog.fecha <= :fechaFin) " +
            "AND a.habilitado = 1 " +
            "ORDER BY prog.fecha DESC")
    List<Atencion> findHistorialGeneral(
            @Param("medicoId") Long medicoId,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin);
}