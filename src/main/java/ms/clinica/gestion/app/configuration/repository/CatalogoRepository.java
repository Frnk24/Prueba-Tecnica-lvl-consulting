package ms.clinica.gestion.app.configuration.repository;

import ms.clinica.gestion.app.configuration.domain.Catalogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatalogoRepository extends JpaRepository<Catalogo, Long>, JpaSpecificationExecutor<Catalogo> {

    @Query("SELECT t FROM Catalogo t WHERE t.codigo = :codigo")
    Optional<Catalogo> getByCodigo(String codigo);

    List<Catalogo> findByPrefijo(String prefijo);

    List<Catalogo> findByNombreContainingIgnoreCase(String nombre);
}
