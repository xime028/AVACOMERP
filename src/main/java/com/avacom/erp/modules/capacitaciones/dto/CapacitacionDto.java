package com.avacom.erp.modules.capacitaciones.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CapacitacionDto {

    private Long id;
    private String titulo;
    private OffsetDateTime fecha;
    private Integer numeroHoras;
    private Integer numeroAsistentes;

    private Long idTipoCapacitacion;
    private String codigoTipo;
    private String tema;

    private Long idUsuario;
    private String nombreUsuario;

    private Long idArchivo;
}