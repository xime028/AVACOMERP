package com.avacom.erp.modules.salidas.entity;

import com.avacom.erp.modules.almacen.entity.ProductoEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "salidas_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalidaItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_salida", nullable = false)
    private SalidaEntity salida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoEntity producto;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @OneToMany(mappedBy = "salidaItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalidaSerialEntity> seriales;
}