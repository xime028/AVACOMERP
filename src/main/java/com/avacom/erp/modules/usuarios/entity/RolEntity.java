package com.avacom.erp.modules.usuarios.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 50, unique = true, nullable = false)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "text")
    private String descripcion;
}