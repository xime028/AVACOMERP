package com.avacom.erp.modules.auditoria.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditoriaDetalleDto {

    private Long id;
    private String campo;
    private String valorAnterior;
    private String valorNuevo;
}