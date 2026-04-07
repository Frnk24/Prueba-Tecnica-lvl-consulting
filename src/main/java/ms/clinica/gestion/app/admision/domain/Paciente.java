package ms.clinica.gestion.app.admision.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ms.clinica.gestion.app.security.domain.Usuario;
import ms.clinica.gestion.core.audit.BaseEntity;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "paciente")
public class Paciente extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paciente_gen")
    @SequenceGenerator(name = "paciente_gen", sequenceName = "paciente_seq", allocationSize = 1)
    @Column(name = "paciente_id")
    private Long pacienteId;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido", length = 150)
    private String apellido;

    @Column(name = "celular", length = 20)
    private String celular;

    @Column(name = "documento_codigo", length = 20)
    private String documentoCodigo;

    @Column(name = "numero_documento", nullable = false, unique = true, length = 20)
    private String numeroDocumento;

    @Column(name = "correo", length = 100)
    private String correo;

    @Column(name = "usuario", unique = true, length = 50)
    private String usuarioPaciente;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @PrePersist
    protected void onPacienteCreate() {
        this.fechaRegistro = LocalDateTime.now();
    }
}