package ms.clinica.gestion.app.atencion.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ms.clinica.gestion.app.cita.domain.Cita;
import ms.clinica.gestion.core.audit.BaseEntity;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "atencion")
public class Atencion extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "atencion_gen")
    @SequenceGenerator(name = "atencion_gen", sequenceName = "atencion_seq", allocationSize = 1)
    @Column(name = "atencion_id")
    private Long atencionId;

    @OneToOne
    @JoinColumn(name = "cita_id", nullable = false, unique = true)
    private Cita cita;

    @Column(name = "motivo_consulta", length = 255)
    private String motivoConsulta;

    @Column(name = "diagnostico", length = 255)
    private String diagnostico;

    @Column(name = "tratamiento", length = 255)
    private String tratamiento;

    @Column(name = "observacion", length = 255)
    private String observacion;
}