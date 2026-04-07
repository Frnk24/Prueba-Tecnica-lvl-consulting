package ms.clinica.gestion.core.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionResponse {

    private String codigo;
    private String mensaje;
    private String detalles;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    public ExceptionResponse(String codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.timestamp = LocalDateTime.now();
    }

    public ExceptionResponse(String codigo, String mensaje, String detalles) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.detalles = detalles;
        this.timestamp = LocalDateTime.now();
    }
}