package ms.clinica.gestion.dto.model.servicio;

import lombok.Getter;
import lombok.Setter;
import ms.clinica.gestion.dto.util.Constantes;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class ServicioFiltroRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer duracion;
    private Integer tiempoTolerancia;

    private Integer start = Constantes.PAGINATION_START;
    private Integer limit = Constantes.PAGINATION_SIZE;

    private Long totalCount;
}
