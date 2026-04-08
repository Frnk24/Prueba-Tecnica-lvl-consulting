package ms.clinica.gestion.dto.model.reporte;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class HistorialAtencionResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long atencionId;
    private Long citaId;
    private Long pacienteId;
    private String pacienteNombre;
    private String pacienteApellido;
    private String pacienteNumeroDocumento;
    private String medicoNombre;
    private String medicoApellido;
    private String servicioNombre;
    private LocalDate fechaProgramacion;
    private LocalTime horaInicio;
    private String motivoConsulta;
    private String diagnostico;
    private String tratamiento;
    private String observacion;
    private String estadoCita;
    private LocalDateTime fechaAtencion;
}