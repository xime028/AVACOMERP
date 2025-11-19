package com.avacom.erp.modules.auditoria.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditoriaFiltroRequest {

    private Long idUsuario;
    private String modulo;
    private String entidad;
    private Long idRegistro;
    private String accion;
    private Boolean exito;
    private OffsetDateTime fechaDesde;
    private OffsetDateTime fechaHasta;
}