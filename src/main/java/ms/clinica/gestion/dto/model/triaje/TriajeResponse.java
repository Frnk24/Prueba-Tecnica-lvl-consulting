package ms.clinica.gestion.dto.model.triaje;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TriajeResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long triajeId;
    private Long citaId;
    private String pacienteNombre;
    private String pacienteApellido;
    private BigDecimal talla;
    private BigDecimal peso;
    private String presion;
    private BigDecimal temperatura;
    private Integer frecuenciaCardiaca;
    private Integer saturacion;
    private LocalDateTime fecha;
    private Short habilitado;
}