package ms.clinica.gestion.core.audit;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "habilitado", nullable = false)
    private short habilitado;

    @Column(name="creado", nullable = false, updatable = false)
    private LocalDateTime creado;

    @Column(name="modificado")
    private LocalDateTime modificado;

    @Column(name="creado_por", length = 100)
    private String creadoPor;

    @Column(name = "modificado_por", length = 100)
    private String modificadoPor;

    @PrePersist
    protected void onCreate() {
        this.creado = LocalDateTime.now();
        this.habilitado = 1;
    }

    @PreUpdate
    protected void onUpdate() {
        this.modificado = LocalDateTime.now();
    }
}
