package com.avacom.erp.modules.serviciotecnico.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "documentos_ordenes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentoOrdenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden_servicio", nullable = false)
    private OrdenServicioEntity ordenServicio;

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