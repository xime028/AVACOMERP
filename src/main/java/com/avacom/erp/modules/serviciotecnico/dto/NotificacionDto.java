package com.avacom.erp.modules.serviciotecnico.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificacionDto {

    private Long id;
    private String mensaje;
    private OffsetDateTime fecha;
    private Long idOrdenServicio;
    private Long idUsuario;
    private String nombreUsuario;
}