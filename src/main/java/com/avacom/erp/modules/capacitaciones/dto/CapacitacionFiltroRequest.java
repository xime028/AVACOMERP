package com.avacom.erp.modules.capacitaciones.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CapacitacionFiltroRequest {

    private String tituloContiene;
    private Long idTipoCapacitacion;
    private Long idUsuario;
    private OffsetDateTime fechaDesde;
    private OffsetDateTime fechaHasta;
}