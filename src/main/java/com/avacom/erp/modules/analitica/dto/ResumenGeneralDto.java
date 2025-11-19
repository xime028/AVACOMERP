package com.avacom.erp.modules.analitica.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumenGeneralDto {

    private long totalClientes;
    private long totalUsuarios;
    private long totalProductos;
    private long totalCapacitaciones;
    private long totalOrdenesServicio;
    private long totalGarantiasActivas;
    private long totalArchivos;
}