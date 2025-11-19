package com.avacom.erp.modules.almacen.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SerialDto {

    private Long id;
    private String numeroSerial;
    private String estado;
    private Long idProducto;
    private String referenciaProducto;
}