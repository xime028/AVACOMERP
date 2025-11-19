package com.avacom.erp.modules.almacen.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoSerialDto {

    private Long id;
    private Long idSerial;
    private String numeroSerial;

    private String tipo;
    private String modulo;
    private Long idReferencia;

    private OffsetDateTime fecha;

    private Long idUsuario;
    private String nombreUsuario;
}