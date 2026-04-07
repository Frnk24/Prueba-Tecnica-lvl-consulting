package ms.clinica.gestion.dto.model.comprobante;

import lombok.Getter;
import lombok.Setter;
import ms.clinica.gestion.dto.util.Constantes;
import java.io.Serializable;

@Getter
@Setter
public class ComprobanteFiltroRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long pacienteId;
    private Long citaId;
    private String estadoCodigo;
    private String tipoCodigo;

    private Integer start = Constantes.PAGINATION_START;
    private Integer limit = Constantes.PAGINATION_SIZE;
    private Long totalCount;
}