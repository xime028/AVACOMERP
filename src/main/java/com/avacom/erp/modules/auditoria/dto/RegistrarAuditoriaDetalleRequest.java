package com.avacom.erp.modules.auditoria.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrarAuditoriaDetalleRequest {

    @NotBlank
    private String campo;

    private String valorAnterior;

    private String valorNuevo;
}