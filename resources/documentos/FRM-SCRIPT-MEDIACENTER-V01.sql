
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


INSERT INTO catalogo (catalogo_id, codigo, nombre, descripcion) VALUES
(1, 'DOC_DNI', 'DNI', 'Documento Nacional de Identidad'),
(2, 'ESP_GEN', 'Medicina General', 'Especialidad en Medicina General'),
(3, 'ESP_CAR', 'Cardiología', 'Especialidad en Cardiología'),
(4, 'EUS001', 'ACTIVO', 'Usuario activo en el sistema'),
(5, 'EUS002', 'INACTIVO', 'Usuario inactivo en el sistema'),
(6, 'EPR001', 'DISPONIBLE', 'Programación con cupos disponibles'),
(7, 'EPR002', 'COMPLETO', 'Programación sin cupos disponibles'),
(8, 'EPR003', 'CANCELADO', 'Programación cancelada'),
(9, 'ECI001', 'RESERVADO', 'Cita reservada por el paciente'),
(10, 'ECI002', 'CANCELADO', 'Cita cancelada'),
(11, 'ECI003', 'EN_ESPERA', 'Paciente pagó y espera triaje'),
(12, 'ECI004', 'EN_TRIAJE', 'Paciente pasó triaje, espera consulta'),
(13, 'ECI005', 'EN_CONSULTA', 'Paciente en el consultorio'),
(14, 'ECI006', 'FINALIZADO', 'Atención médica terminada'),
(15, 'ECO001', 'PENDIENTE', 'Pago pendiente de realizarse'),
(16, 'ECO002', 'PAGADO', 'Pago realizado con éxito'),
(17, 'ECO003', 'ANULADO', 'Pago anulado');
SELECT setval('catalogo_seq', (SELECT MAX(catalogo_id) FROM catalogo));

INSERT INTO rol (rol_id, nombre, descripcion) VALUES
(1, 'ADMIN', 'Administrador del sistema'),
(2, 'MEDICO', 'Personal médico'),
(3, 'RECEPCIONISTA', 'Personal de recepción y caja');
SELECT setval('rol_seq', (SELECT MAX(rol_id) FROM rol));

INSERT INTO servicio (servicio_id, nombre, descripcion, precio, duracion, tiempo_tolerancia) VALUES
(1, 'Medicina General', 'Evaluación médica integral', 50.00, 30, 10),
(2, 'Cardiología', 'Evaluación del sistema cardiovascular', 120.00, 45, 15),
(3, 'Pediatría', 'Atención médica para niños', 80.00, 30, 10);
SELECT setval('servicio_seq', (SELECT MAX(servicio_id) FROM servicio));

INSERT INTO usuario (usuario_id, rol_id, nombre, apellido, numero_documento, especialidad_codigo, usuario, contrasena, estado_codigo) VALUES
(1, 1, 'Admin', 'Sistema', '00000000', NULL, 'admin', 'admin123', 'EUS001'),
(2, 3, 'Lucia', 'Mendez', '11111111', NULL, 'lucia.rec', '123456', 'EUS001'),
(3, 2, 'Dr. Roberto', 'Gomez', '22222222', 'ESP_GEN', 'roberto.med', '123456', 'EUS001'),
(4, 2, 'Dra. Ana', 'Torres', '33333333', 'ESP_CAR', 'ana.med', '123456', 'EUS001');
SELECT setval('usuario_seq', (SELECT MAX(usuario_id) FROM usuario));

INSERT INTO usuario_servicio (usuario_servicio_id, usuario_id, servicio_id) VALUES
(1, 3, 1),
(2, 4, 2);
SELECT setval('usuario_servicio_seq', (SELECT MAX(usuario_servicio_id) FROM usuario_servicio));

INSERT INTO paciente (paciente_id, nombre, apellido, numero_documento, celular) VALUES
(1, 'Carlos', 'Ruiz', '77777777', '999888777'),
(2, 'Maria', 'Salas', '88888888', '999555444'),
(3, 'Jorge', 'Perez', '99999999', '999111222');
SELECT setval('paciente_seq', (SELECT MAX(paciente_id) FROM paciente));

INSERT INTO programacion (programacion_id, usuario_servicio_id, fecha, hora_inicio, hora_fin, cupo_total, cupo_ocupado, estado_codigo) VALUES
(1, 1, CURRENT_DATE, '08:00:00', '13:00:00', 10, 2, 'EPR001'),
(2, 2, CURRENT_DATE, '14:00:00', '18:00:00', 6, 1, 'EPR001');
SELECT setval('programacion_seq', (SELECT MAX(programacion_id) FROM programacion));

INSERT INTO cita (cita_id, paciente_id, programacion_id, inconveniente, estado_codigo) VALUES
(1, 1, 1, 'Dolor de cabeza fuerte', 'ECI006'),
(2, 2, 1, 'Chequeo general', 'ECI003'),
(3, 3, 2, 'Taquicardia', 'ECI001');
SELECT setval('cita_seq', (SELECT MAX(cita_id) FROM cita));

INSERT INTO comprobante (comprobante_id, paciente_id, usuario_id, cita_id, metodo_codigo, total, estado_codigo) VALUES
(1, 1, 2, 1, 'EFECTIVO', 50.00, 'ECO002'),
(2, 2, 2, 2, 'TARJETA', 50.00, 'ECO002');
SELECT setval('comprobante_seq', (SELECT MAX(comprobante_id) FROM comprobante));

INSERT INTO triaje (triaje_id, cita_id, talla, peso, presion, temperatura, frecuencia_cardiaca, saturacion) VALUES
(1, 1, 1.75, 72.5, '120/80', 36.8, 75, 98);
SELECT setval('triaje_seq', (SELECT MAX(triaje_id) FROM triaje));

INSERT INTO atencion (atencion_id, cita_id, motivo_consulta, diagnostico, tratamiento, observacion) VALUES
(1, 1, 'Dolor de cabeza fuerte y fiebre', 'Migraña por estrés', 'Descanso y medicación', 'Paciente debe volver en 1 semana si el dolor persiste');
SELECT setval('atencion_seq', (SELECT MAX(atencion_id) FROM atencion));

INSERT INTO receta (receta_id, atencion_id, medicamento, dosis, frecuencia, duracion) VALUES
(1, 1, 'Paracetamol 500mg', '1 tableta', 'Cada 8 horas', '3 días');
SELECT setval('receta_seq', (SELECT MAX(receta_id) FROM receta));

INSERT INTO evidencia (evidencia_id, atencion_id, url, descripcion) VALUES
(1, 1, 'https://storage.clinica.com/img/radiografia_001.jpg', 'Radiografía de senos paranasales');
SELECT setval('evidencia_seq', (SELECT MAX(evidencia_id) FROM evidencia));