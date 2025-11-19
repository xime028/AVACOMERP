package com.avacom.erp.modules.almacen.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDto {

    private Long id;
    private String referencia;
    private String nombre;
    private String descripcion;
    private Integer stock;
    private Integer stockReservado;
    private Long idCategoria;
    private String nombreCategoria;
}