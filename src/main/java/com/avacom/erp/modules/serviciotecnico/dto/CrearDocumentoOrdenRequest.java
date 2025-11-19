package com.avacom.erp.modules.serviciotecnico.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrearDocumentoOrdenRequest {

    @NotNull
    private Long idDocumento;

    private Boolean firmado;

    private Long idFirmaDocumento;

    private String hashFuncional;
}