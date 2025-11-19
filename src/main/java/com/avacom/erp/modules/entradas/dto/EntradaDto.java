package com.avacom.erp.modules.entradas.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntradaDto {

    private Long id;
    private String consecutivo;
    private String lote;
    private String tipo;
    private String observaciones;
    private OffsetDateTime fecha;

    private Long idProveedor;
    private String nombreProveedor;

    private Long idUsuario;
    private String nombreUsuario;

    private Integer totalItems;
}