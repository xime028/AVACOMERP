package com.avacom.erp.modules.garantias.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarGarantiaRequest {

    private Long idSerial;
    private Long idCliente;
    private Long idProveedor;
    private Long idOrdenServicio;

    private String tipoGarantia;
    private String numeroDocumento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String condiciones;
    private String estado;
    private Long idArchivo;
}