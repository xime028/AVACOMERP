package com.avacom.erp.modules.auditoria.dto;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditoriaEventoDetalleDto {

    private Long id;

    private Long idUsuario;
    private String nombreUsuario;
    private String correoUsuario;

    private String modulo;
    private String entidad;
    private Long idRegistro;
    private String accion;
    private String resumen;
    private String ipOrigen;
    private String userAgent;
    private Boolean exito;
    private OffsetDateTime fecha;

    private List<AuditoriaDetalleDto> detalles;
}