package ms.clinica.gestion.dto.model.reporte;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class RankingEspecialidadResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String especialidadCodigo;
    private String servicioNombre;
    private Long totalAtenciones;
    private Long totalCitas;

    public RankingEspecialidadResponse(String especialidadCodigo,
                                       String servicioNombre, Long totalAtenciones, Long totalCitas) {
        this.especialidadCodigo = especialidadCodigo;
        this.servicioNombre = servicioNombre;
        this.totalAtenciones = totalAtenciones;
        this.totalCitas = totalCitas;
    }
}