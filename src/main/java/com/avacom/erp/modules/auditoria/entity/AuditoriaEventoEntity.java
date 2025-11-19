package com.avacom.erp.modules.auditoria.entity;

import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "auditoria_eventos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditoriaEventoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuario;

    @Column(name = "modulo", length = 40, nullable = false)
    private String modulo;

    @Column(name = "entidad", length = 60, nullable = false)
    private String entidad;

    @Column(name = "id_registro")
    private Long idRegistro;

    @Column(name = "accion", length = 10, nullable = false)
    private String accion;

    @Column(name = "resumen", length = 300)
    private String resumen;

    @Column(name = "ip_origen", length = 45)
    private String ipOrigen;

    @Column(name = "user_agent", columnDefinition = "text")
    private String userAgent;

    @Column(name = "exito", nullable = false)
    private Boolean exito;

    @Column(name = "fecha", nullable = false)
    private OffsetDateTime fecha;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AuditoriaDetalleEntity> detalles;
}