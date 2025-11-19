package com.avacom.erp.modules.serviciotecnico.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrearNotificacionRequest {

    @NotBlank
    private String mensaje;

    private Long idUsuario;
}