package ms.clinica.gestion.app.security.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ms.clinica.gestion.core.audit.BaseEntity;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "usuario_servicio")
public class UsuarioServicio extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_servicio_gen")
    @SequenceGenerator(name = "usuario_servicio_gen", sequenceName = "usuario_servicio_seq", allocationSize = 1)
    @Column(name = "usuario_servicio_id")
    private Long usuarioServicioId;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "servicio_id", nullable = false)
    private Servicio servicio;
}