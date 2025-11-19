package com.avacom.erp.modules.configuracion.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlantillaDocumentoDto {

    /**
     * Código interno: p.ej. "REMISION_SALIDA", "ORDEN_SERVICIO"
     */
    private String codigo;

    /**
     * Nombre legible para UI.
     */
    private String nombre;

    /**
     * Contenido de la plantilla (HTML / texto).
     */
    private String contenido;
}