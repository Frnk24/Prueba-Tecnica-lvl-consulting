package ms.clinica.gestion.dto.model.atencion;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class AtencionRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long atencionId;
    private Long citaId;
    private String motivoConsulta;
    private String diagnostico;
    private String tratamiento;
    private String observacion;
}