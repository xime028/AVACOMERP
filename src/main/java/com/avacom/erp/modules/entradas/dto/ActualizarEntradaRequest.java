package com.avacom.erp.modules.entradas.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarEntradaRequest {

    private String observaciones;
    private OffsetDateTime fecha;
    private String lote;
    private String tipo;
    private Long idProveedor;
    private Long idUsuario;
}