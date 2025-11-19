package com.avacom.erp.modules.entradas.entity;

import com.avacom.erp.modules.almacen.entity.ProductoEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "entradas_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntradaItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entrada", nullable = false)
    private EntradaEntity entrada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoEntity producto;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @OneToMany(mappedBy = "entradaItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EntradaSerialEntity> seriales;
}