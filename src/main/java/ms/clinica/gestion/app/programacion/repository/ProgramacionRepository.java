package ms.clinica.gestion.app.programacion.repository;

import ms.clinica.gestion.app.programacion.domain.Programacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProgramacionRepository extends JpaRepository<Programacion, Long>, JpaSpecificationExecutor<Programacion> {

    @Query("SELECT p FROM Programacion p WHERE p.usuarioServicio.usuarioServicioId = :usuarioServicioId AND p.fecha = :fecha AND p.habilitado = 1")
    List<Programacion> findByUsuarioServicioAndFecha(@Param("usuarioServicioId") Long usuarioServicioId, @Param("fecha") LocalDate fecha);

    List<Programacion> findByFecha(LocalDate fecha);
}
