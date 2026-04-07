package ms.clinica.gestion.app.security.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ms.clinica.gestion.core.audit.BaseEntity;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "usuario_gen")
    @SequenceGenerator( name = "usuario_gen", sequenceName = "usuario_seq", allocationSize = 1)
    @Column(name = "usuario_id")
    private Long usuarioId;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    @Column(name = "nombre" , length = 100)
    private String nombre;

    @Column(name = "apellido" , length = 150)
    private String apellido;

    @Column(name = "celular", length = 20)
    private String celular;

    @Column(name = "correo", length = 100, unique = true)
    private String correo;

    @Column(name = "documento_codigo", length = 20)
    private String documentoCodigo;

    @Column(name = "numero_documento", length = 20)
    private String numeroDocumento;

    @Column(name = "especialidad_codigo", length = 20)
    private String especialidadCodigo;

    @Column(name = "usuario", length = 50, unique = true)
    private String usuario;
    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "estado_codigo", length = 20)
    private String estadoCodigo;

}
