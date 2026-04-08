
CREATE SEQUENCE IF NOT EXISTS catalogo_seq;
CREATE SEQUENCE IF NOT EXISTS rol_seq;
CREATE SEQUENCE IF NOT EXISTS servicio_seq;
CREATE SEQUENCE IF NOT EXISTS usuario_servicio_seq;
CREATE SEQUENCE IF NOT EXISTS usuario_seq;
CREATE SEQUENCE IF NOT EXISTS paciente_seq;
CREATE SEQUENCE IF NOT EXISTS programacion_seq;
CREATE SEQUENCE IF NOT EXISTS cita_seq;
CREATE SEQUENCE IF NOT EXISTS comprobante_seq;
CREATE SEQUENCE IF NOT EXISTS triaje_seq;
CREATE SEQUENCE IF NOT EXISTS atencion_seq;
CREATE SEQUENCE IF NOT EXISTS receta_seq;
CREATE SEQUENCE IF NOT EXISTS evidencia_seq;

CREATE TABLE IF NOT EXISTS catalogo (
    catalogo_id     BIGINT          PRIMARY KEY DEFAULT nextval('catalogo_seq'),
    codigo          VARCHAR(20)     NOT NULL UNIQUE,
    nombre          VARCHAR(100)    NOT NULL,
    descripcion     VARCHAR(255),
    valor           VARCHAR(100),
    prefijo         VARCHAR(10),
    habilitado      SMALLINT        NOT NULL DEFAULT 1,
    creado          TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modificado      TIMESTAMP,
    creado_por      VARCHAR(100),
    modificado_por  VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS rol (
    rol_id          BIGINT          PRIMARY KEY DEFAULT nextval('rol_seq'),
    nombre          VARCHAR(50)     NOT NULL UNIQUE,
    descripcion     VARCHAR(255),
    habilitado      SMALLINT        NOT NULL DEFAULT 1,
    creado          TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modificado      TIMESTAMP,
    creado_por      VARCHAR(100),
    modificado_por  VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS servicio (
    servicio_id         BIGINT          PRIMARY KEY DEFAULT nextval('servicio_seq'),
    nombre              VARCHAR(100)    NOT NULL,
    descripcion         VARCHAR(255),
    precio              NUMERIC(10,2)   NOT NULL DEFAULT 0,
    duracion            INT             NOT NULL,
    tiempo_tolerancia   INT             NOT NULL DEFAULT 0,
    habilitado          SMALLINT        NOT NULL DEFAULT 1,
    creado              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modificado          TIMESTAMP,
    creado_por          VARCHAR(100),
    modificado_por      VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS usuario_servicio (
    usuario_servicio_id BIGINT          PRIMARY KEY DEFAULT nextval('usuario_servicio_seq'),
    usuario_id          BIGINT          NOT NULL,
    servicio_id         BIGINT          NOT NULL,
    habilitado          SMALLINT        NOT NULL DEFAULT 1,
    creado              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modificado          TIMESTAMP,
    creado_por          VARCHAR(100),
    modificado_por      VARCHAR(100),
    UNIQUE (usuario_id, servicio_id)
);

CREATE TABLE IF NOT EXISTS usuario (
    usuario_id              BIGINT          PRIMARY KEY DEFAULT nextval('usuario_seq'),
    rol_id                  BIGINT          NOT NULL,
    nombre                  VARCHAR(100)    NOT NULL,
    apellido                VARCHAR(150),
    celular                 VARCHAR(20),
    correo                  VARCHAR(100)    UNIQUE,
    documento_codigo        VARCHAR(20),
    numero_documento        VARCHAR(20)     NOT NULL,
    especialidad_codigo     VARCHAR(20),
    usuario                 VARCHAR(50)     NOT NULL UNIQUE,
    contrasena              VARCHAR(255)    NOT NULL,
    estado_codigo           VARCHAR(20),
    habilitado              SMALLINT        NOT NULL DEFAULT 1,
    creado                  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modificado              TIMESTAMP,
    creado_por              VARCHAR(100),
    modificado_por          VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS paciente (
    paciente_id         BIGINT          PRIMARY KEY DEFAULT nextval('paciente_seq'),
    usuario_id          BIGINT,
    nombre              VARCHAR(100)    NOT NULL,
    apellido            VARCHAR(150),
    celular             VARCHAR(20),
    documento_codigo    VARCHAR(20),
    numero_documento    VARCHAR(20)     NOT NULL UNIQUE,
    correo              VARCHAR(100),
    usuario             VARCHAR(50)     UNIQUE,
    contrasena          VARCHAR(255),
    fecha_registro      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    habilitado          SMALLINT        NOT NULL DEFAULT 1,
    creado              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modificado          TIMESTAMP,
    creado_por          VARCHAR(100),
    modificado_por      VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS programacion (
    programacion_id     BIGINT          PRIMARY KEY DEFAULT nextval('programacion_seq'),
    usuario_servicio_id BIGINT          NOT NULL,
    fecha               DATE            NOT NULL,
    hora_inicio         TIME            NOT NULL,
    hora_fin            TIME            NOT NULL,
    cupo_total          INT             NOT NULL DEFAULT 1,
    cupo_ocupado        INT             NOT NULL DEFAULT 0,
    estado_codigo       VARCHAR(20),
    habilitado          SMALLINT        NOT NULL DEFAULT 1,
    creado              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modificado          TIMESTAMP,
    creado_por          VARCHAR(100),
    modificado_por      VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS cita (
    cita_id             BIGINT          PRIMARY KEY DEFAULT nextval('cita_seq'),
    paciente_id         BIGINT          NOT NULL,
    programacion_id     BIGINT          NOT NULL,
    inconveniente       VARCHAR(255),
    fecha_reserva       TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_confirmacion  TIMESTAMP,
    fecha_atencion      TIMESTAMP,
    observacion         VARCHAR(255),
    estado_codigo       VARCHAR(20),
    habilitado          SMALLINT        NOT NULL DEFAULT 1,
    creado              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modificado          TIMESTAMP,
    creado_por          VARCHAR(100),
    modificado_por      VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS comprobante (
    comprobante_id      BIGINT          PRIMARY KEY DEFAULT nextval('comprobante_seq'),
    paciente_id         BIGINT          NOT NULL,
    usuario_id          BIGINT          NOT NULL,
    cita_id             BIGINT          NOT NULL,
    metodo_codigo       VARCHAR(20),
    tipo_codigo         VARCHAR(20),
    moneda_codigo       VARCHAR(20),
    numero_cuota        INT             DEFAULT 1,
    monto_pagado        NUMERIC(10,2)   NOT NULL DEFAULT 0,
    total               NUMERIC(10,2)   NOT NULL,
    fecha               TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado_codigo       VARCHAR(20),
    habilitado          SMALLINT        NOT NULL DEFAULT 1,
    creado              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modificado          TIMESTAMP,
    creado_por          VARCHAR(100),
    modificado_por      VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS triaje (
    triaje_id           BIGINT          PRIMARY KEY DEFAULT nextval('triaje_seq'),
    cita_id             BIGINT          NOT NULL UNIQUE,
    talla               NUMERIC(5,2),
    peso                NUMERIC(5,2),
    presion             VARCHAR(20),
    temperatura         NUMERIC(4,1),
    frecuencia_cardiaca INT,
    saturacion          INT,
    fecha               TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    habilitado          SMALLINT        NOT NULL DEFAULT 1,
    creado              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modificado          TIMESTAMP,
    creado_por          VARCHAR(100),
    modificado_por      VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS atencion (
    atencion_id         BIGINT          PRIMARY KEY DEFAULT nextval('atencion_seq'),
    cita_id             BIGINT          NOT NULL UNIQUE,
    motivo_consulta     VARCHAR(255),
    diagnostico         VARCHAR(255),
    tratamiento         VARCHAR(255),
    observacion       	VARCHAR(255),
    habilitado          SMALLINT        NOT NULL DEFAULT 1,
    creado              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modificado          TIMESTAMP,
    creado_por          VARCHAR(100),
    modificado_por      VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS receta (
    receta_id           BIGINT          PRIMARY KEY DEFAULT nextval('receta_seq'),
    atencion_id         BIGINT          NOT NULL,
    medicamento         VARCHAR(150),
    dosis               VARCHAR(100),
    frecuencia          VARCHAR(100),
    duracion            VARCHAR(100),
    habilitado          SMALLINT        NOT NULL DEFAULT 1,
    creado              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modificado          TIMESTAMP,
    creado_por          VARCHAR(100),
    modificado_por      VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS evidencia (
    evidencia_id        BIGINT          PRIMARY KEY DEFAULT nextval('evidencia_seq'),
    atencion_id         BIGINT          NOT NULL,
    url                 VARCHAR(500)    NOT NULL,
    descripcion         VARCHAR(255),
    habilitado          SMALLINT        NOT NULL DEFAULT 1,
    creado              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modificado          TIMESTAMP,
    creado_por          VARCHAR(100),
    modificado_por      VARCHAR(100)
);

ALTER TABLE usuario
    ADD CONSTRAINT fk_usuario_rol
    FOREIGN KEY (rol_id)                REFERENCES rol(rol_id);

ALTER TABLE usuario_servicio
    ADD CONSTRAINT fk_usuarioservicio_usuario
    FOREIGN KEY (usuario_id)            REFERENCES usuario(usuario_id);

ALTER TABLE usuario_servicio
    ADD CONSTRAINT fk_usuarioservicio_servicio
    FOREIGN KEY (servicio_id)           REFERENCES servicio(servicio_id);

ALTER TABLE paciente
    ADD CONSTRAINT fk_paciente_usuario
    FOREIGN KEY (usuario_id)            REFERENCES usuario(usuario_id);

ALTER TABLE programacion
    ADD CONSTRAINT fk_programacion_usuario
    FOREIGN KEY (usuario_id)            REFERENCES usuario(usuario_id);

ALTER TABLE programacion
    ADD CONSTRAINT fk_programacion_usuarioservicio
    FOREIGN KEY (usuario_servicio_id)   REFERENCES usuario_servicio(usuario_servicio_id);

ALTER TABLE cita
    ADD CONSTRAINT fk_cita_paciente
    FOREIGN KEY (paciente_id)           REFERENCES paciente(paciente_id);

ALTER TABLE cita
    ADD CONSTRAINT fk_cita_programacion
    FOREIGN KEY (programacion_id)       REFERENCES programacion(programacion_id);

ALTER TABLE comprobante
    ADD CONSTRAINT fk_comprobante_paciente
    FOREIGN KEY (paciente_id)           REFERENCES paciente(paciente_id);

ALTER TABLE comprobante
    ADD CONSTRAINT fk_comprobante_usuario
    FOREIGN KEY (usuario_id)            REFERENCES usuario(usuario_id);

ALTER TABLE comprobante
    ADD CONSTRAINT fk_comprobante_cita
    FOREIGN KEY (cita_id)               REFERENCES cita(cita_id);

ALTER TABLE triaje
    ADD CONSTRAINT fk_triaje_cita
    FOREIGN KEY (cita_id)               REFERENCES cita(cita_id);

ALTER TABLE atencion
    ADD CONSTRAINT fk_atencion_cita
    FOREIGN KEY (cita_id)               REFERENCES cita(cita_id);

ALTER TABLE receta
    ADD CONSTRAINT fk_receta_atencion
    FOREIGN KEY (atencion_id)           REFERENCES atencion(atencion_id);

ALTER TABLE evidencia
    ADD CONSTRAINT fk_evidencia_atencion
    FOREIGN KEY (atencion_id)           REFERENCES atencion(atencion_id);

ALTER SEQUENCE catalogo_seq         OWNED BY catalogo.catalogo_id;
ALTER SEQUENCE rol_seq              OWNED BY rol.rol_id;
ALTER SEQUENCE servicio_seq         OWNED BY servicio.servicio_id;
ALTER SEQUENCE usuario_servicio_seq OWNED BY usuario_servicio.usuario_servicio_id;
ALTER SEQUENCE usuario_seq          OWNED BY usuario.usuario_id;
ALTER SEQUENCE paciente_seq         OWNED BY paciente.paciente_id;
ALTER SEQUENCE programacion_seq     OWNED BY programacion.programacion_id;
ALTER SEQUENCE cita_seq             OWNED BY cita.cita_id;
ALTER SEQUENCE comprobante_seq      OWNED BY comprobante.comprobante_id;
ALTER SEQUENCE triaje_seq           OWNED BY triaje.triaje_id;
ALTER SEQUENCE atencion_seq         OWNED BY atencion.atencion_id;
ALTER SEQUENCE receta_seq           OWNED BY receta.receta_id;
ALTER SEQUENCE evidencia_seq        OWNED BY evidencia.evidencia_id;

CREATE INDEX idx_usuario_rol                ON usuario(rol_id);
CREATE INDEX idx_usuarioservicio_usuario    ON usuario_servicio(usuario_id);
CREATE INDEX idx_usuarioservicio_servicio   ON usuario_servicio(servicio_id);
CREATE INDEX idx_programacion_usuario       ON programacion(usuario_id);
CREATE INDEX idx_programacion_ususerv       ON programacion(usuario_servicio_id);
CREATE INDEX idx_programacion_fecha         ON programacion(fecha);
CREATE INDEX idx_cita_paciente              ON cita(paciente_id);
CREATE INDEX idx_cita_programacion          ON cita(programacion_id);
CREATE INDEX idx_cita_estado                ON cita(estado_codigo);
CREATE INDEX idx_comprobante_paciente       ON comprobante(paciente_id);
CREATE INDEX idx_comprobante_cita           ON comprobante(cita_id);
CREATE INDEX idx_triaje_cita                ON triaje(cita_id);
CREATE INDEX idx_atencion_cita              ON atencion(cita_id);
CREATE INDEX idx_receta_atencion            ON receta(atencion_id);
CREATE INDEX idx_evidencia_atencion         ON evidencia(atencion_id);