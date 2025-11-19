package com.avacom.erp.modules.almacen.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarCategoriaRequest {

    @Size(max = 50)
    private String nombre;

    @Size(max = 100)
    private String descripcion;
}