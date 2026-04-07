package ms.clinica.gestion.dto.model.usuarioservicio;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class UsuarioServicioRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long usuarioServicioId;
    private Long usuarioId;
    private Long servicioId;
}