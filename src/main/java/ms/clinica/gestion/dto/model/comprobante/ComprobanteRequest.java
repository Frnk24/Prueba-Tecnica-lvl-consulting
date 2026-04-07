package ms.clinica.gestion.dto.model.comprobante;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class ComprobanteRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long comprobanteId;
    private Long pacienteId;
    private Long usuarioId;
    private Long citaId;
    private String metodoCodigo;
    private String tipoCodigo;
    private String monedaCodigo;
    private Integer numeroCuota;
    private BigDecimal montoPagado;
    private BigDecimal total;
    private String estadoCodigo;
}