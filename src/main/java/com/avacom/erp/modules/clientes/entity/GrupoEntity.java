package com.avacom.erp.modules.clientes.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "grupos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GrupoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 50, unique = true, nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "grupo", fetch = FetchType.LAZY)
    private List<ClienteEntity> clientes;
}