package ms.clinica.gestion.app.reporte.repository;

import ms.clinica.gestion.dto.model.reporte.RankingMedicoResponse;
import ms.clinica.gestion.dto.model.reporte.RankingEspecialidadResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ms.clinica.gestion.app.cita.domain.Cita;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReporteRankingRepository extends JpaRepository<Cita, Long> {

    @Query("SELECT new ms.clinica.gestion.dto.model.reporte.RankingMedicoResponse(" +
            "u.usuarioId, u.nombre, u.apellido, u.especialidadCodigo, s.nombre, " +
            "COUNT(DISTINCT a.atencionId), COUNT(c.citaId)) " +
            "FROM Cita c " +
            "JOIN c.programacion prog " +
            "JOIN prog.usuarioServicio us " +
            "JOIN us.usuario u " +
            "JOIN us.servicio s " +
            "LEFT JOIN Atencion a ON a.cita.citaId = c.citaId " +
            "WHERE (CAST(:fechaInicio AS date) IS NULL OR prog.fecha >= :fechaInicio) " +
            "AND (CAST(:fechaFin AS date) IS NULL OR prog.fecha <= :fechaFin) " +
            "AND c.habilitado = 1 " +
            "GROUP BY u.usuarioId, u.nombre, u.apellido, u.especialidadCodigo, s.nombre " +
            "ORDER BY COUNT(DISTINCT a.atencionId) DESC")
    List<RankingMedicoResponse> findRankingMedicos(
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin);

    @Query("SELECT new ms.clinica.gestion.dto.model.reporte.RankingEspecialidadResponse(" +
            "u.especialidadCodigo, s.nombre, " +
            "COUNT(DISTINCT a.atencionId), COUNT(c.citaId)) " +
            "FROM Cita c " +
            "JOIN c.programacion prog " +
            "JOIN prog.usuarioServicio us " +
            "JOIN us.usuario u " +
            "JOIN us.servicio s " +
            "LEFT JOIN Atencion a ON a.cita.citaId = c.citaId " +
            "WHERE (CAST(:fechaInicio AS date) IS NULL OR prog.fecha >= :fechaInicio) " +
            "AND (CAST(:fechaFin AS date) IS NULL OR prog.fecha <= :fechaFin) " +
            "AND c.habilitado = 1 " +
            "GROUP BY u.especialidadCodigo, s.nombre " +
            "ORDER BY COUNT(DISTINCT a.atencionId) DESC")
    List<RankingEspecialidadResponse> findRankingEspecialidades(
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin);
}