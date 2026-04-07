package ms.clinica.gestion.dto.model.comprobante;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ComprobanteResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long comprobanteId;
    private Long pacienteId;
    private String pacienteNombre;
    private String pacienteApellido;
    private Long usuarioId;
    private String usuarioNombre;
    private Long citaId;
    private String metodoCodigo;
    private String tipoCodigo;
    private String monedaCodigo;
    private Integer numeroCuota;
    private BigDecimal montoPagado;
    private BigDecimal total;
    private LocalDateTime fecha;
    private String estadoCodigo;
    private Short habilitado;
}