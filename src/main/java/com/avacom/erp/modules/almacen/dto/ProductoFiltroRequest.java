package com.avacom.erp.modules.almacen.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoFiltroRequest {

    private String nombre;
    private String referencia;
    private Long idCategoria;
}