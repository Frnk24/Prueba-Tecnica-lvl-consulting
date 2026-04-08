package ms.clinica.gestion.dto.model.reporte;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class HistorialTriajeResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long triajeId;
    private Long citaId;
    private Long pacienteId;
    private String pacienteNombre;
    private String pacienteApellido;
    private LocalDate fechaCita;
    private String medicoNombre;
    private String servicioNombre;
    private BigDecimal talla;
    private BigDecimal peso;
    private String presion;
    private BigDecimal temperatura;
    private Integer frecuenciaCardiaca;
    private Integer saturacion;
    private LocalDateTime fechaTriaje;
}