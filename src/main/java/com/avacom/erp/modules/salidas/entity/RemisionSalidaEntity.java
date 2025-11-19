package com.avacom.erp.modules.salidas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "remisiones_salidas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RemisionSalidaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_salida", nullable = false)
    private SalidaEntity salida;

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