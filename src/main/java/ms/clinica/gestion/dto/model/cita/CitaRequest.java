package ms.clinica.gestion.dto.model.cita;

import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
public class CitaRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long citaId;
    private Long pacienteId;
    private Long programacionId;
    private String inconveniente;
    private String observacion;
}
