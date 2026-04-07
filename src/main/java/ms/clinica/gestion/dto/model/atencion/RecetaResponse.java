package ms.clinica.gestion.dto.model.atencion;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class RecetaResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long recetaId;
    private Long atencionId;
    private String medicamento;
    private String dosis;
    private String frecuencia;
    private String duracion;
    private Short habilitado;
}