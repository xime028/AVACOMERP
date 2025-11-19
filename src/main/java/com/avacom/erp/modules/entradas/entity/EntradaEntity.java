package com.avacom.erp.modules.entradas.entity;

import com.avacom.erp.modules.proveedores.entity.ProveedorEntity;
import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "entradas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntradaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "consecutivo", length = 50, unique = true, nullable = false)
    private String consecutivo;

    @Column(name = "observaciones", columnDefinition = "text")
    private String observaciones;

    @Column(name = "fecha", nullable = false)
    private OffsetDateTime fecha;

    @Column(name = "lote", length = 100)
    private String lote;

    @Column(name = "tipo", length = 50)
    private String tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proveedor", nullable = false)
    private ProveedorEntity proveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "entrada", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EntradaItemEntity> items;

    @OneToMany(mappedBy = "entrada", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RemisionEntradaEntity> remisiones;
}