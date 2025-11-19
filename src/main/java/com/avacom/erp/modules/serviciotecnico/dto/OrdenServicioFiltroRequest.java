package com.avacom.erp.modules.serviciotecnico.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenServicioFiltroRequest {

    private Integer consecutivo;
    private String estado;
    private String prioridad;
    private Long idCliente;
    private Long idTecnico;
    private Long idSerial;
    private OffsetDateTime fechaDesde;
    private OffsetDateTime fechaHasta;
}