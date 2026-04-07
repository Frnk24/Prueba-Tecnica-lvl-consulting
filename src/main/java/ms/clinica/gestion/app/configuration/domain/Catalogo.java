package ms.clinica.gestion.app.configuration.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ms.clinica.gestion.core.audit.BaseEntity;

import java.io.Serializable;

@Setter
@Getter
@Table(name="catalogo")
@Entity
public class Catalogo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catalogo_gen")
    @SequenceGenerator(name = "catalogo_gen", sequenceName = "catalogo_seq", allocationSize = 1)
    @Column(name = "catalogo_id")
    private Long catalogoId;

    @Column(name = "codigo", nullable = false, unique = true, length = 20)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "valor", length = 100)
    private String valor;

    @Column(name = "prefijo", length = 10)
    private String prefijo;

}
