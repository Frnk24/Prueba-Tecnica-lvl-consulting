package ms.clinica.gestion.dto.model.programacion;

import lombok.Getter;
import lombok.Setter;
import ms.clinica.gestion.dto.util.Constantes;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ProgramacionFiltroRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long usuarioId;
    private Long usuarioServicioId;
    private Long servicioId;
    private LocalDate fecha;
    private String estadoCodigo;

    private Integer start = Constantes.PAGINATION_START;
    private Integer limit = Constantes.PAGINATION_SIZE;
    private Long totalCount;
}