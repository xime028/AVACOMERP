package com.avacom.erp.modules.capacitaciones.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarCapacitacionRequest {

    private String titulo;
    private OffsetDateTime fecha;
    private Integer numeroHoras;
    private Integer numeroAsistentes;
    private Long idTipoCapacitacion;
    private Long idUsuario;
    private Long idArchivo;
}