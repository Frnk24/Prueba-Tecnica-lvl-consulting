package ms.clinica.gestion.app.atencion.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ms.clinica.gestion.core.audit.BaseEntity;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "evidencia")
public class Evidencia extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "evidencia_gen")
    @SequenceGenerator(name = "evidencia_gen", sequenceName = "evidencia_seq", allocationSize = 1)
    @Column(name = "evidencia_id")
    private Long evidenciaId;

    @ManyToOne
    @JoinColumn(name = "atencion_id", nullable = false)
    private Atencion atencion;

    @Column(name = "url", nullable = false, length = 500)
    private String url;

    @Column(name = "descripcion", length = 255)
    private String descripcion;
}