package ms.clinica.gestion.app.security.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ms.clinica.gestion.core.audit.BaseEntity;

import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "rol")
public class Rol extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rol_gen")
    @SequenceGenerator( name = "rol_gen", sequenceName = "rol_seq", allocationSize = 1)
    @Column( name = "rol_id")
    private Long rolId;

    @Column(name = "nombre", length = 50, nullable = false, unique = true)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;
}
