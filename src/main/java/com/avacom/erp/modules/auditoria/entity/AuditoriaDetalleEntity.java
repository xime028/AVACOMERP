package com.avacom.erp.modules.auditoria.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "auditoria_detalles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditoriaDetalleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK evento
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evento", nullable = false)
    private AuditoriaEventoEntity evento;

    @Column(name = "campo", length = 80, nullable = false)
    private String campo;

    @Column(name = "valor_anterior", columnDefinition = "text")
    private String valorAnterior;

    @Column(name = "valor_nuevo", columnDefinition = "text")
    private String valorNuevo;
}