package com.avacom.erp.modules.salidas.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalidaFiltroRequest {

    private String consecutivo;
    private Long idCliente;
    private Long idUsuario;
    private String departamento;
    private String ciudad;
    private OffsetDateTime fechaDesde;
    private OffsetDateTime fechaHasta;
}