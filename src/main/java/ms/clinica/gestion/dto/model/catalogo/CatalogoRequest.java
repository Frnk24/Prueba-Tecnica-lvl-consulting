package ms.clinica.gestion.dto.model.catalogo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CatalogoRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long catalogoId;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String valor;
    private String prefijo;
}
