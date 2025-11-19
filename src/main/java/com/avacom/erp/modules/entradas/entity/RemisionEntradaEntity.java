package com.avacom.erp.modules.entradas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "remisiones_entradas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RemisionEntradaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entrada", nullable = false)
    private EntradaEntity entrada;

    @Column(name = "id_documento", nullable = false)
    private Long idDocumento;

    @Column(name = "firmado", nullable = false)
    private Boolean firmado;

    @Column(name = "id_firma_documento")
    private Long idFirmaDocumento;

    @Column(name = "hash_funcional", length = 128)
    private String hashFuncional;

    @Column(name = "fecha", nullable = false)
    private OffsetDateTime fecha;
}
