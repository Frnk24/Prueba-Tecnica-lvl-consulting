package ms.clinica.gestion.dto.model.atencion;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class AtencionResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long atencionId;
    private Long citaId;
    private String pacienteNombre;
    private String pacienteApellido;
    private String medicoNombre;
    private String medicoApellido;
    private String servicioNombre;
    private String motivoConsulta;
    private String diagnostico;
    private String tratamiento;
    private String observacion;
    private LocalDateTime creado;
    private Short habilitado;

    private List<RecetaResponse> recetas;
    private List<EvidenciaResponse> evidencias;
}