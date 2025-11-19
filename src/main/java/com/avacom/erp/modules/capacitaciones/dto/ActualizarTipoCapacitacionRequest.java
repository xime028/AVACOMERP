package com.avacom.erp.modules.capacitaciones.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarTipoCapacitacionRequest {

    @Size(max = 20)
    private String codigoTipo;

    @Size(max = 50)
    private String tema;
}