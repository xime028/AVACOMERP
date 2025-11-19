package com.avacom.erp.modules.almacen.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarSerialRequest {

    private String estado;
    private Long idProducto;
}