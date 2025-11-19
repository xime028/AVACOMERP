package com.avacom.erp.modules.capacitaciones.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "tipo_capacitaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoCapacitacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_tipo", length = 20, nullable = false)
    private String codigoTipo;

    @Column(name = "tema", length = 50, nullable = false)
    private String tema;

    @Column(name = "fecha", nullable = false)
    private OffsetDateTime fecha;

    @OneToMany(mappedBy = "tipoCapacitacion", fetch = FetchType.LAZY)
    private List<CapacitacionEntity> capacitaciones;
}