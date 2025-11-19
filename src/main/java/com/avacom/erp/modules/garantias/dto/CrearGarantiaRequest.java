package com.avacom.erp.modules.garantias.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrearGarantiaRequest {

    @NotNull
    private Long idSerial;

    @NotNull
    private Long idCliente;

    @NotNull
    private Long idProveedor;

    // opcional
    private Long idOrdenServicio;

    @NotBlank
    @Size(max = 50)
    private String tipoGarantia;

    @NotBlank
    @Size(max = 50)
    private String numeroDocumento;

    @NotNull
    private LocalDate fechaInicio;

    @NotNull
    private LocalDate fechaFin;

    private String condiciones;

    @NotBlank
    @Size(max = 30)
    private String estado;

    // opcional
    private Long idArchivo;

    @NotNull
    private Long idUsuario;
}