package com.avacom.erp.modules.salidas.entity;

import com.avacom.erp.modules.clientes.entity.ClienteEntity;
import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "salidas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalidaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "consecutivo", length = 50, unique = true, nullable = false)
    private String consecutivo;

    @Column(name = "recibe", columnDefinition = "text")
    private String recibe;

    @Column(name = "departamento", length = 50)
    private String departamento;

    @Column(name = "ciudad", length = 50)
    private String ciudad;

    @Column(name = "fecha_salida", nullable = false)
    private OffsetDateTime fechaSalida;

    @Column(name = "observaciones", columnDefinition = "text")
    private String observaciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private ClienteEntity cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "salida", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalidaItemEntity> items;

    @OneToMany(mappedBy = "salida", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RemisionSalidaEntity> remisiones;
}