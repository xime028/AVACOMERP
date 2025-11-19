package com.avacom.erp.modules.salidas.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrearSalidaRequest {

    /**
     * Opcional: si no se envía, se genera automáticamente.
     */
    private String consecutivo;

    private String recibe;
    private String departamento;
    private String ciudad;

    /**
     * Opcional: si es null se usa la fecha actual.
     */
    private OffsetDateTime fechaSalida;

    private String observaciones;

    @NotNull
    private Long idCliente;

    @NotNull
    private Long idUsuario;

    @NotNull
    private List<CrearSalidaItemRequest> items;
}