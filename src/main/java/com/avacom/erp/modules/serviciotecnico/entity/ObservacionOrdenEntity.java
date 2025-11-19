package com.avacom.erp.modules.serviciotecnico.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "observaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ObservacionOrdenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_observaciones")
    private Long id;

    @Column(name = "observacion", columnDefinition = "text", nullable = false)
    private String observacion;

    @Column(name = "fecha", nullable = false)
    private OffsetDateTime fecha;

    @Column(name = "id_archivo")
    private Long idArchivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden_servicio", nullable = false)
    private OrdenServicioEntity ordenServicio;
}