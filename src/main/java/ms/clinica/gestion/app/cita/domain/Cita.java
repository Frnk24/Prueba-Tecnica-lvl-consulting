package ms.clinica.gestion.app.cita.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ms.clinica.gestion.app.admision.domain.Paciente;
import ms.clinica.gestion.app.programacion.domain.Programacion;
import ms.clinica.gestion.core.audit.BaseEntity;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "cita")
public class Cita extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cita_gen")
    @SequenceGenerator(name = "cita_gen", sequenceName = "cita_seq", allocationSize = 1)
    @Column(name = "cita_id")
    private Long citaId;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "programacion_id", nullable = false)
    private Programacion programacion;

    @Column(name = "inconveniente", length = 255)
    private String inconveniente;

    @Column(name = "fecha_reserva", nullable = false, updatable = false)
    private LocalDateTime fechaReserva;

    @Column(name = "fecha_confirmacion")
    private LocalDateTime fechaConfirmacion;

    @Column(name = "fecha_atencion")
    private LocalDateTime fechaAtencion;

    @Column(name = "observacion", length = 255)
    private String observacion;

    @Column(name = "estado_codigo", length = 20)
    private String estadoCodigo;

    @PrePersist
    protected void onCitaCreate() {
        this.fechaReserva = LocalDateTime.now();
    }
}