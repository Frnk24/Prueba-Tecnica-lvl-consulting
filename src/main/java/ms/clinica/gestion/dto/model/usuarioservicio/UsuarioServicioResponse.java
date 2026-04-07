package ms.clinica.gestion.dto.model.usuarioservicio;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class UsuarioServicioResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long usuarioServicioId;
    private Long usuarioId;
    private String usuarioNombre;
    private String usuarioApellido;
    private Long servicioId;
    private String servicioNombre;
    private Short habilitado;
}