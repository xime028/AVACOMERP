package com.avacom.erp.modules.almacen.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrearCategoriaRequest {

    @NotBlank
    @Size(max = 50)
    private String nombre;

    @Size(max = 100)
    private String descripcion;
}
