package com.avacom.erp.modules.serviciotecnico.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ObservacionDto {

    private Long id;
    private String observacion;
    private OffsetDateTime fecha;
    private Long idArchivo;
    private Long idOrdenServicio;
}