package com.avacom.erp.modules.capacitaciones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrearTipoCapacitacionRequest {

    @NotBlank
    @Size(max = 20)
    private String codigoTipo;

    @NotBlank
    @Size(max = 50)
    private String tema;
}