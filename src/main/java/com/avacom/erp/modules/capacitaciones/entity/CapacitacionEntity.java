package com.avacom.erp.modules.capacitaciones.entity;

import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "capacitaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CapacitacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;

    @Column(name = "fecha", nullable = false)
    private OffsetDateTime fecha;

    @Column(name = "numero_horas")
    private Integer numeroHoras;

    @Column(name = "numero_asistentes")
    private Integer numeroAsistentes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tema", nullable = false)
    private TipoCapacitacionEntity tipoCapacitacion;

    /**
     * FK a archivos.id_archivo
     */
    @Column(name = "id_archivo")
    private Long idArchivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuario;
}