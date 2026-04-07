package ms.clinica.gestion.dto.model.programacion;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class ProgramacionRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long programacionId;
    private Long usuarioServicioId;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Integer cupoTotal;
    private String estadoCodigo;
}