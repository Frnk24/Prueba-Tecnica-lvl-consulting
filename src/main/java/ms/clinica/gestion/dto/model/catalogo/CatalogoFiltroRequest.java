package ms.clinica.gestion.dto.model.catalogo;

import lombok.Getter;
import lombok.Setter;
import ms.clinica.gestion.dto.util.Constantes;

import java.io.Serializable;

@Getter
@Setter
public class CatalogoFiltroRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    private String codigo;
    private String nombre;
    private String prefijo;
    private Integer start = Constantes.PAGINATION_START;
    private Integer limit = Constantes.PAGINATION_SIZE;
    private Long totalCount;
}
