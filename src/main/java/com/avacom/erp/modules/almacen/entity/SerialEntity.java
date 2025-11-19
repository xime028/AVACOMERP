package com.avacom.erp.modules.almacen.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seriales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SerialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_serial", length = 150, unique = true, nullable = false)
    private String numeroSerial;

    @Column(name = "estado", length = 30, nullable = false)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoEntity producto;
}