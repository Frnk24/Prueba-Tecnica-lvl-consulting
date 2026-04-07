package ms.clinica.gestion.app.security.repository;

import ms.clinica.gestion.app.security.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {
        Optional<Usuario> findByUsuario(String usuario);
        Optional<Usuario> findByCorreo(String correo);
        Optional<Usuario> findByNumeroDocumento(String numeroDocumento);

}
