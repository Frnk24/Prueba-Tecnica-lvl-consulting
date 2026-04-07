package ms.clinica.gestion.dto.model.servicio;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
public class ServicioRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long servicioId;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer duracion;
    private Integer tiempoTolerancia;

}
