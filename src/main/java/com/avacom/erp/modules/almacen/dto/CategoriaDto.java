package com.avacom.erp.modules.almacen.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaDto {

    private Long id;
    private String nombre;
    private String descripcion;
}