package ms.clinica.gestion.app.atencion.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ms.clinica.gestion.core.audit.BaseEntity;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "receta")
public class Receta extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receta_gen")
    @SequenceGenerator(name = "receta_gen", sequenceName = "receta_seq", allocationSize = 1)
    @Column(name = "receta_id")
    private Long recetaId;

    @ManyToOne
    @JoinColumn(name = "atencion_id", nullable = false)
    private Atencion atencion;

    @Column(name = "medicamento", length = 150)
    private String medicamento;

    @Column(name = "dosis", length = 100)
    private String dosis;

    @Column(name = "frecuencia", length = 100)
    private String frecuencia;

    @Column(name = "duracion", length = 100)
    private String duracion;
}