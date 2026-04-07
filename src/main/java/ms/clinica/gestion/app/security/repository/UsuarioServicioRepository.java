package ms.clinica.gestion.app.security.repository;

import ms.clinica.gestion.app.security.domain.UsuarioServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioServicioRepository extends JpaRepository<UsuarioServicio, Long>, JpaSpecificationExecutor<UsuarioServicio> {

    Optional<UsuarioServicio> findByUsuarioUsuarioIdAndServicioServicioId(
            Long usuarioId, Long servicioId);
}