package ms.clinica.gestion.dto.model.cita;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class CitaResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long citaId;
    private Long pacienteId;
    private String pacienteNombre;
    private String pacienteApellido;
    private String pacienteNumeroDocumento;
    private Long programacionId;
    private Long usuarioServicioId;
    private String medicoNombre;
    private String medicoApellido;
    private String servicioNombre;
    private String inconveniente;
    private LocalDateTime fechaReserva;
    private LocalDateTime fechaConfirmacion;
    private LocalDateTime fechaAtencion;
    private String observacion;
    private String estadoCodigo;
    private Short habilitado;
}