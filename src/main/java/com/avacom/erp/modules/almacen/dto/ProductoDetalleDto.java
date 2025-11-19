package com.avacom.erp.modules.almacen.dto;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDetalleDto {

    private Long id;
    private String referencia;
    private String nombre;
    private String descripcion;
    private Integer stock;
    private Integer stockReservado;
    private Long idCategoria;
    private String nombreCategoria;
    private OffsetDateTime fecha;
    private List<SerialDto> seriales;
}