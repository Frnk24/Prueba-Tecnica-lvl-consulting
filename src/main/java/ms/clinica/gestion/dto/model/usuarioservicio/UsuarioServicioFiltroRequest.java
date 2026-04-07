package ms.clinica.gestion.dto.model.usuarioservicio;

import lombok.Getter;
import lombok.Setter;
import ms.clinica.gestion.dto.util.Constantes;
import java.io.Serializable;

@Getter
@Setter
public class UsuarioServicioFiltroRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long usuarioId;
    private Long servicioId;

    private Integer start = Constantes.PAGINATION_START;
    private Integer limit = Constantes.PAGINATION_SIZE;
    private Long totalCount;
}