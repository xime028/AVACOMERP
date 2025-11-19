package com.avacom.erp.modules.usuarios.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolDto {

    private Long id;
    private String nombre;
    private String descripcion;
}