package ms.clinica.gestion.app.programacion.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ms.clinica.gestion.app.security.domain.UsuarioServicio;
import ms.clinica.gestion.core.audit.BaseEntity;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "programacion")
public class Programacion extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "programacion_gen")
    @SequenceGenerator(name = "programacion_gen", sequenceName = "programacion_seq", allocationSize = 1)
    @Column(name = "programacion_id")
    private Long programacionId;

    @ManyToOne
    @JoinColumn(name = "usuario_servicio_id", nullable = false)
    private UsuarioServicio usuarioServicio;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFin;

    @Column(name = "cupo_total", nullable = false)
    private Integer cupoTotal;

    @Column(name = "cupo_ocupado", nullable = false)
    private Integer cupoOcupado = 0;

    @Column(name = "estado_codigo", length = 20)
    private String estadoCodigo;
}