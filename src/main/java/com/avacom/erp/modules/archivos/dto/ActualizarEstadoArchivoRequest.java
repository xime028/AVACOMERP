package com.avacom.erp.modules.archivos.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarEstadoArchivoRequest {

    /**
     * Ej: ACTIVO, INACTIVO, ELIMINADO
     */
    @NotBlank
    private String estado;
}
