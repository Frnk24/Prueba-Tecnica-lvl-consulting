package ms.clinica.gestion.dto.model.cita;

import lombok.Getter;
import lombok.Setter;
import ms.clinica.gestion.dto.util.Constantes;
import java.io.Serializable;

@Getter
@Setter
public class CitaFiltroRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long pacienteId;
    private Long programacionId;
    private Long usuarioId;
    private String estadoCodigo;

    private Integer start = Constantes.PAGINATION_START;
    private Integer limit = Constantes.PAGINATION_SIZE;
    private Long totalCount;
}