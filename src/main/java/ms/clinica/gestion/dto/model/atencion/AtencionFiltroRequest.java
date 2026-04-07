package ms.clinica.gestion.dto.model.atencion;

import lombok.Getter;
import lombok.Setter;
import ms.clinica.gestion.dto.util.Constantes;
import java.io.Serializable;

@Getter
@Setter
public class AtencionFiltroRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long citaId;
    private Long pacienteId;
    private Long medicoId;

    private Integer start = Constantes.PAGINATION_START;
    private Integer limit = Constantes.PAGINATION_SIZE;
    private Long totalCount;
}