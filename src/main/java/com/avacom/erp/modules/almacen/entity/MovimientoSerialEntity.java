package com.avacom.erp.modules.almacen.entity;

import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "movimiento_serial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoSerialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_serial", nullable = false)
    private SerialEntity serial;

    @Column(name = "tipo", length = 20, nullable = false)
    private String tipo; // ENTRADA, SALIDA, SERVICIO, GARANTIA, AJUSTE...

    @Column(name = "modulo", length = 20, nullable = false)
    private String modulo; // ENTRADAS, SALIDAS, SERVICIO, GARANTIAS...

    @Column(name = "id_referencia", nullable = false)
    private Long idReferencia; // id_entrada, id_salida, id_orden_servicio, etc.

    @Column(name = "fecha", nullable = false)
    private OffsetDateTime fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuario;
}