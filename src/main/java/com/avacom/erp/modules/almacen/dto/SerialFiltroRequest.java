package com.avacom.erp.modules.almacen.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SerialFiltroRequest {

    private Long idProducto;
    private String numeroSerialContiene;
    private String estado;
}