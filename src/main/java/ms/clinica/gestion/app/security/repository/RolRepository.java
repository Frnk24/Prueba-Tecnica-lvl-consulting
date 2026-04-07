package ms.clinica.gestion.app.security.repository;

import ms.clinica.gestion.app.security.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>, JpaSpecificationExecutor<Rol> {
    Optional<Rol> findByNombre(String nombre);
}
