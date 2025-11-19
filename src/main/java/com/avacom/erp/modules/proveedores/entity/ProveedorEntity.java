package com.avacom.erp.modules.proveedores.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "proveedores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProveedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 150, nullable = false)
    private String nombre;

    @Column(name = "nit", length = 50, unique = true, nullable = false)
    private String nit;

    @Column(name = "correo", length = 100)
    private String correo;

    @Column(name = "telefono", length = 30)
    private String telefono;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "fecha", nullable = false)
    private OffsetDateTime fecha;
}