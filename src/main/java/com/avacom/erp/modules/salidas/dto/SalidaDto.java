package com.avacom.erp.modules.salidas.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalidaDto {

    private Long id;
    private String consecutivo;
    private String recibe;
    private String departamento;
    private String ciudad;
    private OffsetDateTime fechaSalida;
    private String observaciones;

    private Long idCliente;
    private String nombreCliente;

    private Long idUsuario;
    private String nombreUsuario;

    private Integer totalItems;
}