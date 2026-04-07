package ms.clinica.gestion.dto.util;

public class Constantes {

    public static final String BLANK_SPACE = " ";
    public static final String GUION = "-";
    public static final Short HABILITADO = 1;
    public static final Short INHABILITADO = 0;
    public static final String EMPTY = "";
    public static final String SUCCESS = "success";
    public static final Integer PAGINATION_START = 0;
    public static final Integer PAGINATION_SIZE = 20;

    public static final String MESSAGE_SAVE = "Registro guardado correctamente";
    public static final String MESSAGE_DELETE = "Registro eliminado correctamente";

    private Constantes() {}

    public static class EstadoProgramacion {
        public static final String DISPONIBLE = "EPR001";
        public static final String COMPLETO = "EPR002";
        public static final String CANCELADO = "EPR003";
        private EstadoProgramacion() {}
    }

    public static class EstadoCita {
        public static final String RESERVADO = "ECI001";
        public static final String CANCELADO = "ECI002";
        public static final String EN_ESPERA = "ECI003";
        public static final String EN_TRIAJE = "ECI004";
        public static final String EN_CONSULTA = "ECI005";
        public static final String FINALIZADO = "ECI006";
        private EstadoCita() {}
    }

    public static class EstadoComprobante {
        public static final String PENDIENTE = "ECO001";
        public static final String PAGADO = "ECO002";
        public static final String ANULADO = "ECO003";

        private EstadoComprobante() {}
    }

    public static class EstadoUsuario {
        public static final String ACTIVO = "EUS001";
        public static final String INACTIVO = "EUS002";

        private EstadoUsuario() {}
    }


}