package com.avacom.erp.modules.garantias.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GarantiaDto {

    private Long id;

    private Long idSerial;
    private String numeroSerial;

    private Long idCliente;
    private String nombreCliente;

    private Long idProveedor;
    private String nombreProveedor;

    private Long idOrdenServicio;
    private Integer consecutivoOrdenServicio;

    private String tipoGarantia;
    private String numeroDocumento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;

    private Long idArchivo;
    private OffsetDateTime fechaRegistro;
}