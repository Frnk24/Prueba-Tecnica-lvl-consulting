package ms.clinica.gestion.dto.model.paciente;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class PacienteResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long pacienteId;
    private Long usuarioId;
    private String nombre;
    private String apellido;
    private String celular;
    private String documentoCodigo;
    private String numeroDocumento;
    private String correo;
    private String usuario;
    private LocalDateTime fechaRegistro;
    private Short habilitado;
}