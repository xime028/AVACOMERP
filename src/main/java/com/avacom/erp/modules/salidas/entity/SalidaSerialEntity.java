package com.avacom.erp.modules.salidas.entity;

import com.avacom.erp.modules.almacen.entity.SerialEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "salidas_seriales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalidaSerialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_salida_item", nullable = false)
    private SalidaItemEntity salidaItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_serial", nullable = false)
    private SerialEntity serial;
}