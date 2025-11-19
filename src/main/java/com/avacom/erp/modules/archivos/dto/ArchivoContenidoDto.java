package com.avacom.erp.modules.archivos.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArchivoContenidoDto {

    private Long id;
    private String nombreOriginal;
    private String tipoContenido;
    private Long tamanioBytes;
    private byte[] contenido;
}