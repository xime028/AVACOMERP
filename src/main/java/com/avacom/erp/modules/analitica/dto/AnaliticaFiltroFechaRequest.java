package com.avacom.erp.modules.analitica.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnaliticaFiltroFechaRequest {

    /**
     * Rango de fechas para los KPI.
     * Si es null, el servicio usará un rango por defecto (últimos 30 días).
     */
    private OffsetDateTime fechaDesde;
    private OffsetDateTime fechaHasta;
}