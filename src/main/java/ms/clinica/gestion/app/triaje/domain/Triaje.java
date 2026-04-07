package ms.clinica.gestion.app.triaje.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ms.clinica.gestion.app.cita.domain.Cita;
import ms.clinica.gestion.core.audit.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "triaje")
public class Triaje extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "triaje_gen")
    @SequenceGenerator(name = "triaje_gen", sequenceName = "triaje_seq", allocationSize = 1)
    @Column(name = "triaje_id")
    private Long triajeId;

    @OneToOne
    @JoinColumn(name = "cita_id", nullable = false, unique = true)
    private Cita cita;

    @Column(name = "talla", precision = 5, scale = 2)
    private BigDecimal talla;

    @Column(name = "peso", precision = 5, scale = 2)
    private BigDecimal peso;

    @Column(name = "presion", length = 20)
    private String presion;

    @Column(name = "temperatura", precision = 4, scale = 1)
    private BigDecimal temperatura;

    @Column(name = "frecuencia_cardiaca")
    private Integer frecuenciaCardiaca;

    @Column(name = "saturacion")
    private Integer saturacion;

    @Column(name = "fecha", nullable = false, updatable = false)
    private LocalDateTime fecha;

    @PrePersist
    protected void onTriajeCreate() {
        this.fecha = LocalDateTime.now();
    }
}