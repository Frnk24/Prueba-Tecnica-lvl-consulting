package ms.clinica.gestion.dto.model.reporte;

import lombok.Getter;
import lombok.Setter;
import ms.clinica.gestion.dto.util.Constantes;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ReporteFiltroRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long pacienteId;
    private Long medicoId;
    private Long servicioId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private Integer start = Constantes.PAGINATION_START;
    private Integer limit = Constantes.PAGINATION_SIZE;
    private Long totalCount;
}