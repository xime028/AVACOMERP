package com.avacom.erp.modules.auditoria.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditoriaEventoDto {

    private Long id;

    private Long idUsuario;
    private String nombreUsuario;

    private String modulo;
    private String entidad;
    private Long idRegistro;
    private String accion;
    private String resumen;
    private String ipOrigen;
    private String userAgent;
    private Boolean exito;
    private OffsetDateTime fecha;
}