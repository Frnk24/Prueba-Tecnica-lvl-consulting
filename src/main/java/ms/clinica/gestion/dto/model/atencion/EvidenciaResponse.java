package ms.clinica.gestion.dto.model.atencion;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class EvidenciaResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long evidenciaId;
    private Long atencionId;
    private String url;
    private String descripcion;
    private Short habilitado;
}