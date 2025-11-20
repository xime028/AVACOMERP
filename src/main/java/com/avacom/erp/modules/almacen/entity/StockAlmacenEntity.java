package com.avacom.erp.modules.almacen.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stock_almacen",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_producto", "id_almacen"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockAlmacenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoEntity producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_almacen", nullable = false)
    private AlmacenEntity almacen;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "stock_reservado", nullable = false)
    private Integer stockReservado;
}