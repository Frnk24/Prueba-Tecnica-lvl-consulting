package ms.clinica.gestion.dto.model.reporte;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class RankingMedicoResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long usuarioId;
    private String medicoNombre;
    private String medicoApellido;
    private String especialidadCodigo;
    private String servicioNombre;
    private Long totalAtenciones;
    private Long totalCitas;

    public RankingMedicoResponse(Long usuarioId, String medicoNombre,
                                 String medicoApellido, String especialidadCodigo,
                                 String servicioNombre, Long totalAtenciones, Long totalCitas) {
        this.usuarioId = usuarioId;
        this.medicoNombre = medicoNombre;
        this.medicoApellido = medicoApellido;
        this.especialidadCodigo = especialidadCodigo;
        this.servicioNombre = servicioNombre;
        this.totalAtenciones = totalAtenciones;
        this.totalCitas = totalCitas;
    }
}
