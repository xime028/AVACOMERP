package com.avacom.erp.modules.archivos.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArchivoFiltroRequest {

    private String nombreOriginalContiene;
    private String tipoContenido;
    private String estado;
    private Long idUsuarioCargador;
    private OffsetDateTime fechaDesde;
    private OffsetDateTime fechaHasta;
}