package ms.clinica.gestion.dto.model.usuario;

import lombok.Getter;
import lombok.Setter;
import ms.clinica.gestion.dto.util.Constantes;

import java.io.Serializable;

@Getter
@Setter
public class UsuarioFiltroRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long rolId;
    private String nombre;
    private String apellido;
    private String correo;
    private String numeroDocumento;
    private String especialidadCodigo;
    private String usuario;
    private String estadoCodigo;

    private Integer start = Constantes.PAGINATION_START;
    private Integer limit = Constantes.PAGINATION_SIZE;
    private Long totalCount;
}
