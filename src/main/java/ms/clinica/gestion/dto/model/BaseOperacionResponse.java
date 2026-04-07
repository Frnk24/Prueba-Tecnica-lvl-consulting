package ms.clinica.gestion.dto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class BaseOperacionResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String codigo;
    private String mensaje;

    public BaseOperacionResponse(String codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }
}