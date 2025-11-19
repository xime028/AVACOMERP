package com.avacom.erp.modules.almacen.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrearProductoRequest {

    @NotBlank
    @Size(max = 50)
    private String referencia;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @Size(max = 512)
    private String descripcion;

    @NotNull
    private Long idCategoria;

    @NotNull
    private Integer stock;

    private Integer stockReservado;
}
