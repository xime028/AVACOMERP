package com.avacom.erp.modules.entradas.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntradaFiltroRequest {

    private String consecutivo;
    private Long idProveedor;
    private Long idUsuario;
    private String lote;
    private String tipo;
    private OffsetDateTime fechaDesde;
    private OffsetDateTime fechaHasta;
}