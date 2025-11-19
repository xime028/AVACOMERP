package com.avacom.erp.modules.usuarios.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contrasena_hash", nullable = false, columnDefinition = "text")
    private String contrasenaHash;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "usuario", length = 100, unique = true, nullable = false)
    private String usuario;

    @Column(name = "correo", length = 100, unique = true, nullable = false)
    private String correo;

    @Column(name = "telefono", length = 30)
    private String telefono;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", nullable = false)
    private RolEntity rol;

    @Column(name = "fecha_creacion", nullable = false)
    private OffsetDateTime fechaCreacion;

    @Column(name = "id_archivo")
    private Long idArchivo;
}