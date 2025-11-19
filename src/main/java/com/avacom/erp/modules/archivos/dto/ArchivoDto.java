package com.avacom.erp.modules.archivos.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArchivoDto {

    private Long id;
    private String bucket;
    private String claveObjeto;
    private String idVersion;
    private String nombreOriginal;
    private String tipoContenido;
    private Long tamanioBytes;
    private String etag;

    private Long idUsuarioCargador;
    private String nombreUsuarioCargador;

    private String estado;
    private OffsetDateTime fechaCreacion;
    private OffsetDateTime fechaActualizacion;
}