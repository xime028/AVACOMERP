package com.avacom.erp.modules.almacen.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockAlmacenDto {

    private Long id;

    private Long idProducto;
    private String referenciaProducto;
    private String nombreProducto;

    private Long idAlmacen;
    private String codigoAlmacen;
    private String nombreAlmacen;

    private Integer stock;
    private Integer stockReservado;
}
