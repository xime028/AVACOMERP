package com.avacom.erp.modules.salidas.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RemisionSalidaDto {

    private Long id;
    private Long idSalida;
    private Long idDocumento;
    private Boolean firmado;
    private Long idFirmaDocumento;
    private String hashFuncional;
    private OffsetDateTime fecha;
}