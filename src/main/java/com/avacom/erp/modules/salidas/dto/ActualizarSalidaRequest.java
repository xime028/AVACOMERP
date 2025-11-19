package com.avacom.erp.modules.salidas.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarSalidaRequest {

    private String recibe;
    private String departamento;
    private String ciudad;
    private OffsetDateTime fechaSalida;
    private String observaciones;
    private Long idCliente;
    private Long idUsuario;
}