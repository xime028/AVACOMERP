package com.avacom.erp.modules.almacen.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoSerialFiltroRequest {

    private Long idSerial;
    private String numeroSerial;
    private String tipo;
    private String modulo;
    private Long idUsuario;
    private OffsetDateTime fechaDesde;
    private OffsetDateTime fechaHasta;
}