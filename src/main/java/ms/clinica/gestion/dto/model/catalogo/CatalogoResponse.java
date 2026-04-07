package ms.clinica.gestion.dto.model.catalogo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class CatalogoResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long catalogoId;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String valor;
    private String prefijo;
    private short habilitado;
}
