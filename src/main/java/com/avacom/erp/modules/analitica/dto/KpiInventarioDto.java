package com.avacom.erp.modules.analitica.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KpiInventarioDto {

    private long totalProductos;
    private long stockTotal;
    private long stockReservadoTotal;
    private long productosSinStock;
}