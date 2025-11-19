package com.avacom.erp.modules.configuracion.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarPlantillaDocumentoRequest {

    /**
     * Contenido completo de la plantilla.
     */
    @NotBlank
    private String contenido;
}