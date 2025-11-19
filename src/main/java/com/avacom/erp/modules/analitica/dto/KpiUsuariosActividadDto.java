package com.avacom.erp.modules.analitica.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KpiUsuariosActividadDto {

    private OffsetDateTime fechaDesde;
    private OffsetDateTime fechaHasta;

    private long totalUsuarios;
    private long usuariosActivos;
    private long usuariosInactivos;

    private long eventosAuditoria;
    private long eventosExitosos;
    private long eventosFallidos;
}