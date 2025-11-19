package com.avacom.erp.modules.entradas.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrearRemisionEntradaRequest {

    @NotNull
    private Long idDocumento;

    private Boolean firmado;

    private Long idFirmaDocumento;

    private String hashFuncional;
}
