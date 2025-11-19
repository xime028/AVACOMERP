package com.avacom.erp.modules.almacen.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "referencia", length = 50, unique = true, nullable = false)
    private String referencia;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaEntity categoria;

    @Column(name = "descripcion", columnDefinition = "text")
    private String descripcion;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "stock_reservado", nullable = false)
    private Integer stockReservado;

    @Column(name = "fecha", nullable = false)
    private OffsetDateTime fecha;

    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    private List<SerialEntity> seriales;
}