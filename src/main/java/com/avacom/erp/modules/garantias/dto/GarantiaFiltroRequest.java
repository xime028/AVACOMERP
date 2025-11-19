package com.avacom.erp.modules.garantias.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GarantiaFiltroRequest {

    private Long idSerial;
    private String numeroSerial;

    private Long idCliente;
    private Long idProveedor;
    private Long idOrdenServicio;

    private String tipoGarantia;
    private String numeroDocumento;
    private String estado;

    private LocalDate fechaInicioDesde;
    private LocalDate fechaFinHasta;
}
