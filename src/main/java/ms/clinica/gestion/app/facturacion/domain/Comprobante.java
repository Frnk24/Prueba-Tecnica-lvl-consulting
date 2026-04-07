package ms.clinica.gestion.app.facturacion.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ms.clinica.gestion.app.admision.domain.Paciente;
import ms.clinica.gestion.app.cita.domain.Cita;
import ms.clinica.gestion.app.security.domain.Usuario;
import ms.clinica.gestion.core.audit.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comprobante")
public class Comprobante extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comprobante_gen")
    @SequenceGenerator(name = "comprobante_gen", sequenceName = "comprobante_seq", allocationSize = 1)
    @Column(name = "comprobante_id")
    private Long comprobanteId;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "cita_id", nullable = false)
    private Cita cita;

    @Column(name = "metodo_codigo", length = 20)
    private String metodoCodigo;

    @Column(name = "tipo_codigo", length = 20)
    private String tipoCodigo;

    @Column(name = "moneda_codigo", length = 20)
    private String monedaCodigo;

    @Column(name = "numero_cuota")
    private Integer numeroCuota;

    @Column(name = "monto_pagado", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoPagado;

    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column(name = "fecha", nullable = false, updatable = false)
    private LocalDateTime fecha;

    @Column(name = "estado_codigo", length = 20)
    private String estadoCodigo;

    @PrePersist
    protected void onComprobanteCreate() {
        this.fecha = LocalDateTime.now();
    }
}