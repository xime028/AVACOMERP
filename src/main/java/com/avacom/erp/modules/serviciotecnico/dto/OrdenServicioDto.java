package com.avacom.erp.modules.serviciotecnico.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenServicioDto {

    private Long id;
    private Integer consecutivo;
    private String descripcionDano;
    private String estado;
    private String prioridad;
    private OffsetDateTime fechaIngreso;
    private OffsetDateTime fechaActualizacion;

    private Long idCliente;
    private String nombreCliente;

    private Long idTecnico;
    private String nombreTecnico;

    private Long idSerial;
    private String numeroSerial;
}