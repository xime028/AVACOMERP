package com.avacom.erp.modules.capacitaciones.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoCapacitacionDto {

    private Long id;
    private String codigoTipo;
    private String tema;
    private OffsetDateTime fecha;
}