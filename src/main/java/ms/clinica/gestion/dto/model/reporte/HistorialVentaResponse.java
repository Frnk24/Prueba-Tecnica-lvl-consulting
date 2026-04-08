package ms.clinica.gestion.dto.model.reporte;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class HistorialVentaResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long comprobanteId;
    private Long citaId;
    private String pacienteNombre;
    private String pacienteApellido;
    private String pacienteNumeroDocumento;
    private String usuarioNombre;
    private String tipoCodigo;
    private String metodoCodigo;
    private String monedaCodigo;
    private Integer numeroCuota;
    private BigDecimal montoPagado;
    private BigDecimal total;
    private String estadoCodigo;
    private LocalDateTime fecha;
}