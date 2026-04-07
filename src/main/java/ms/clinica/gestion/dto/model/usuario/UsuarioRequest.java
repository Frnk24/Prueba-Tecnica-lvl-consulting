package ms.clinica.gestion.dto.model.usuario;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UsuarioRequest implements Serializable {

    public static final long serialVersionUID = 1L;

    private Long usuarioId;
    private Long rolId;
    private String nombre;
    private String apellido;
    private String celular;
    private String correo;
    private String documentoCodigo;
    private String numeroDocumento;
    private String especialidadCodigo;
    private String usuario;
    private String contrasena;
    private String estadoCodigo;

}
