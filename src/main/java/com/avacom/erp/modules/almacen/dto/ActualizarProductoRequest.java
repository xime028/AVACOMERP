package com.avacom.erp.modules.almacen.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarProductoRequest {

    @Size(max = 100)
    private String nombre;

    @Size(max = 512)
    private String descripcion;

    private Long idCategoria;

    private Integer stock;

    private Integer stockReservado;
}