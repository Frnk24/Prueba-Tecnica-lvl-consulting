# MS Clínica Gestión

## Resumen del planteamiento

Sistema de gestión clínica desarrollado con arquitectura de capas modular
siguiendo los estándares de LVL Consulting. El sistema cubre el flujo 
completo de atención médica: desde el registro del paciente hasta la 
finalización de la consulta.

## Tecnologías

- Java 25
- Spring Boot 4.0.5
- PostgreSQL 18
- JPA / Hibernate 7
- ModelMapper 3.2.0
- Lombok

## Arquitectura

<img width="709" height="449" alt="image" src="https://github.com/user-attachments/assets/017d526a-a965-4360-a9be-587c6265b7b7" />

### Capas por módulo

<img width="874" height="80" alt="image" src="https://github.com/user-attachments/assets/a5ba9c90-4dc4-4884-a882-930befae26c9" />

- **Controller(api)**: Expone los endpoints REST. Sin lógica de negocio.
- **Facade**: Orquesta y transforma entre DTOs y entidades.
- **Service**: Contiene toda la lógica y validaciones de negocio.
- **Repository**: Acceso a datos mediante Spring Data JPA.
- **Domain**: Entidades JPA mapeadas a la base de datos.

### Módulos implementados
- app/
- configuration/  → Catálogo de parámetros del sistema
- security/       → Roles, usuarios, servicios y asignaciones
- admision/       → Gestión de pacientes
- programacion/   → Agenda médica y cupos
- cita/           → Reserva y gestión de citas
- facturacion/    → Comprobantes de pago
- triaje/         → Evaluación inicial del paciente
- atencion/       → Consulta médica, recetas y evidencias

## Flujo del negocio

1. Configurar roles y usuarios (médicos/recepcionistas)
2. Crear servicios médicos y asignarlos a médicos
3. Programar agenda del médico (fecha, horario, cupos)
4. Registrar paciente
5. Reservar cita → estado: RESERVADO
6. Registrar pago → estado: EN_ESPERA
7. Registrar triaje → estado: EN_TRIAJE
8. Registrar atención médica → estado: EN_CONSULTA
9. Agregar recetas y evidencias
10. Finalizar atención → estado: FINALIZADO

## Diagrama de contexto

<img width="6456" height="2616" alt="LVL - GRU02" src="https://github.com/user-attachments/assets/c1e498fa-e00a-4e83-af8b-c68e3f59617f" />

## Modelo ER

<img width="1353" height="803" alt="image" src="https://github.com/user-attachments/assets/503ea7ec-a10c-42c5-b586-870f54adac94" />

## Configuración y ejecución

### Requisitos
- Java 25
- Docker y Docker Compose instalados (para levantar la base de datos)
- IntelliJ IDEA (o tu IDE preferido)

### 1. Levantar la Base de Datos
El proyecto incluye un entorno preconfigurado con Docker que levanta PostgreSQL 18 en el puerto 5433 y ejecuta automáticamente el script `MEDICENTER_SCRIPT.sql` con la estructura de tablas y datos iniciales.

Abre una terminal en la raíz del proyecto y ejecuta:

```bash
docker-compose up -d
```

### application.properties
```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/BD_MEDICENTER
spring.datasource.username=postgres
spring.datasource.password=admin
spring.jpa.hibernate.ddl-auto=none
spring.jpa.open-in-view=false
```

### Ejecutar el proyecto
Una vez que el contenedor de la base de datos esté corriendo, puedes iniciar la aplicación de dos formas:

Opción A (Recomendada): Ejecutar la clase principal MsClinicaGestionApplication.java directamente desde IntelliJ IDEA.

Opción B: Ejecutar mediante terminal usando Maven:

```bash
mvn spring-boot:run
```

## Endpoints principales

| Módulo | Método | Ruta |
|--------|--------|------|
| Catálogo | POST | /api/catalogo/find |
| Rol | POST | /api/rol/find |
| Usuario | POST | /api/usuarios/find |
| Servicio | POST | /api/servicio/find |
| Paciente | POST | /api/paciente/find |
| Programación | POST | /api/programacion/find |
| Cita | POST | /api/cita/find |
| Comprobante | POST | /api/comprobante/find |
| Triaje | POST | /api/triaje/find |
| Atención | POST | /api/atencion/find |

## Planteamiento de la solución

### Decisiones de arquitectura

Se implementó una arquitectura en capas modular organizada por dominio 
de negocio siguiendo los estándares de LVL Consulting. La decisión de 
organizar por módulo de negocio en lugar de por tipo de clase permite 
escalar el sistema agregando nuevos módulos sin afectar los existentes.

La capa Facade fue clave para desacoplar el Controller del Service, 
permitiendo que cada capa tenga una responsabilidad única y clara.

### Decisiones de modelo de datos

Se implementó borrado lógico en todas las tablas mediante el campo 
habilitado (0/1) para preservar el historial clínico, que por 
regulaciones médicas nunca debe eliminarse físicamente.

Los estados de la cita se diseñaron siguiendo el flujo real del negocio:
RESERVADO → EN_ESPERA → EN_TRIAJE → EN_CONSULTA → FINALIZADO

La tabla programacion maneja cupos automáticamente. Al reservar una 
cita el cupo_ocupado incrementa, al cancelar decrementa. Cuando 
cupo_ocupado = cupo_total la programación pasa a estado COMPLETO.

### Decisiones técnicas

Se utilizó JpaSpecificationExecutor para filtros dinámicos en lugar 
de múltiples métodos en el repositorio, reduciendo el código y 
haciendo los filtros más flexibles.

Se eligió ModelMapper con estrategia STRICT para evitar mapeos 
incorrectos entre entidades con nombres de campos similares.

Las relaciones @ManyToOne y @OneToOne se mapearon con las entidades 
completas en lugar de solo los IDs, aprovechando el lazy loading 
de JPA y facilitando la navegación de relaciones anidadas en las 
respuestas.
