package ms.clinica.gestion.app.security.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ms.clinica.gestion.core.audit.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "servicio")
public class Servicio extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "servicio_gen")
    @SequenceGenerator(name = "servicio_gen", sequenceName = "servicio_seq", allocationSize = 1)
    @Column(name = "servicio_id")
    private Long servicioId;

    @Column(name = "nombre", length = 100, nullable = false, unique = true)
    private String nombre;

    private String descripcion;

    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "duracion", nullable = false)
    private Integer duracion;

    @Column(name = "tiempo_tolerancia", nullable = false)
    private Integer tiempoTolerancia;

}
