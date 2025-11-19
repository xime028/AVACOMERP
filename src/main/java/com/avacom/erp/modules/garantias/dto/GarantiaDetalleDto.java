package com.avacom.erp.modules.garantias.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GarantiaDetalleDto {

    private Long id;

    private Long idSerial;
    private String numeroSerial;
    private Long idProducto;
    private String referenciaProducto;
    private String nombreProducto;

    private Long idCliente;
    private String nombreCliente;
    private String correoCliente;
    private String telefonoCliente;

    private Long idProveedor;
    private String nombreProveedor;
    private String correoProveedor;
    private String telefonoProveedor;

    private Long idOrdenServicio;
    private Integer consecutivoOrdenServicio;
    private String estadoOrdenServicio;

    private String tipoGarantia;
    private String numeroDocumento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String condiciones;
    private String estado;

    private Long idArchivo;
    private String nombreArchivoOriginal;

    private Long idUsuario;
    private String nombreUsuario;
    private String correoUsuario;

    private OffsetDateTime fechaRegistro;
}