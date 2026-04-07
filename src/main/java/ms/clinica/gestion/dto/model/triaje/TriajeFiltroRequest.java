package ms.clinica.gestion.dto.model.triaje;

import lombok.Getter;
import lombok.Setter;
import ms.clinica.gestion.dto.util.Constantes;
import java.io.Serializable;

@Getter
@Setter
public class TriajeFiltroRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long citaId;
    private Long pacienteId;

    private Integer start = Constantes.PAGINATION_START;
    private Integer limit = Constantes.PAGINATION_SIZE;
    private Long totalCount;
}