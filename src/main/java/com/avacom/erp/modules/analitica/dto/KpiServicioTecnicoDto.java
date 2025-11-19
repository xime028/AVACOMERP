package com.avacom.erp.modules.analitica.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KpiServicioTecnicoDto {

    private OffsetDateTime fechaDesde;
    private OffsetDateTime fechaHasta;

    private long totalOrdenes;
    private long ordenesAbiertas;
    private long ordenesEnProceso;
    private long ordenesCerradas;
    private double promedioHorasCierre;
}