package com.avacom.erp.modules.salidas.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrearSalidaItemRequest {

    @NotNull
    private Long idProducto;

    @NotNull
    private Integer cantidad;

    /**
     * Lista de IDs de seriales asociados a este item.
     * Puede ser vacía si el producto no lleva serial.
     */
    private List<Long> idsSeriales;
}