package com.avacom.erp.modules.entradas.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrearEntradaRequest {

    /**
     * Opcional: si no se envía, se genera automáticamente.
     */
    private String consecutivo;

    private String observaciones;

    /**
     * Opcional: si es null se usa la fecha actual.
     */
    private OffsetDateTime fecha;

    private String lote;

    private String tipo;

    @NotNull
    private Long idProveedor;

    @NotNull
    private Long idUsuario;

    @NotNull
    private List<CrearEntradaItemRequest> items;
}