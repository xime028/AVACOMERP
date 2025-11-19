package com.avacom.erp.modules.serviciotecnico.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentoOrdenDto {

    private Long id;
    private Long idOrdenServicio;
    private Long idDocumento;
    private Boolean firmado;
    private Long idFirmaDocumento;
    private String hashFuncional;
    private OffsetDateTime fecha;
}