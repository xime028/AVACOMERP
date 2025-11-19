package com.avacom.erp.modules.analitica.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KpiEntradasSalidasDto {

    private OffsetDateTime fechaDesde;
    private OffsetDateTime fechaHasta;

    private long totalEntradas;
    private long totalSalidas;

    private long totalItemsEntradas;
    private long totalItemsSalidas;
}