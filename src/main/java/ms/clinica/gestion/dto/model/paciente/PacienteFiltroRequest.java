package ms.clinica.gestion.dto.model.paciente;

import lombok.Getter;
import lombok.Setter;
import ms.clinica.gestion.dto.util.Constantes;
import java.io.Serializable;

@Getter
@Setter
public class PacienteFiltroRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String apellido;
    private String numeroDocumento;
    private String correo;

    private Integer start = Constantes.PAGINATION_START;
    private Integer limit = Constantes.PAGINATION_SIZE;
    private Long totalCount;
}