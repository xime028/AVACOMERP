package com.avacom.erp.modules.configuracion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "empresa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpresaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 150, nullable = false)
    private String nombre;

    @Column(name = "nit", length = 20, unique = true, nullable = false)
    private String nit;

    @Column(name = "direccion", columnDefinition = "text")
    private String direccion;

    @Column(name = "telefono", length = 50)
    private String telefono;

    @Column(name = "correo", length = 100)
    private String correo;

    /**
     * FK a tabla archivos.id_archivo
     */
    @Column(name = "logo")
    private Long idLogoArchivo;

    @Column(name = "fecha_registro", nullable = false)
    private OffsetDateTime fechaRegistro;
}