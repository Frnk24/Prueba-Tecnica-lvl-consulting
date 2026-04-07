package ms.clinica.gestion.dto.model.rol;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RolRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long rolId;
    private String nombre;
    private String descripcion;
}
