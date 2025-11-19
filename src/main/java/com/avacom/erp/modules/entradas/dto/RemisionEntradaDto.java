package com.avacom.erp.modules.entradas.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RemisionEntradaDto {

    private Long id;
    private Long idEntrada;
    private Long idDocumento;
    private Boolean firmado;
    private Long idFirmaDocumento;
    private String hashFuncional;
    private OffsetDateTime fecha;
}