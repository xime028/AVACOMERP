package com.avacom.erp.modules.entradas.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntradaItemDto {

    private Long id;
    private Long idProducto;
    private String nombreProducto;
    private Integer cantidad;
    private List<EntradaSerialDto> seriales;
}