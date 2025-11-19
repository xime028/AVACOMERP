package com.avacom.erp.modules.salidas.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalidaItemDto {

    private Long id;
    private Long idProducto;
    private String nombreProducto;
    private Integer cantidad;
    private List<SalidaSerialDto> seriales;
}