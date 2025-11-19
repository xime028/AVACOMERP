package com.avacom.erp.modules.entradas.entity;

import com.avacom.erp.modules.almacen.entity.SerialEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "entradas_seriales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntradaSerialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entrada_item", nullable = false)
    private EntradaItemEntity entradaItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_serial", nullable = false)
    private SerialEntity serial;
}