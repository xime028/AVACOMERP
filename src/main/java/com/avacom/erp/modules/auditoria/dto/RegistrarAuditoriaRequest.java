package com.avacom.erp.modules.auditoria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrarAuditoriaRequest {

    @NotNull
    private Long idUsuario;

    @NotBlank
    private String modulo;

    @NotBlank
    private String entidad;

    private Long idRegistro;

    /**
     * Ej: CREATE, UPDATE, DELETE, LOGIN, LOGOUT, ERROR, etc.
     */
    @NotBlank
    private String accion;

    private String resumen;

    private String ipOrigen;

    private String userAgent;

    @NotNull
    private Boolean exito;

    private List<RegistrarAuditoriaDetalleRequest> detalles;
}