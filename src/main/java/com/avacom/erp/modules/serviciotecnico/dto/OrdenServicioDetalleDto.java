package com.avacom.erp.modules.serviciotecnico.dto;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenServicioDetalleDto {

    private Long id;
    private Integer consecutivo;
    private String descripcionDano;
    private String estado;
    private String prioridad;
    private String observacionesGenerales;
    private OffsetDateTime fechaIngreso;
    private OffsetDateTime fechaActualizacion;

    private Long idCliente;
    private String nombreCliente;

    private Long idTecnico;
    private String nombreTecnico;

    private Long idSerial;
    private String numeroSerial;

    private Long idFalla;
    private Long idSolucion;
    private Long idArchivo;

    private List<ObservacionDto> observaciones;
    private List<DocumentoOrdenDto> documentos;
    private List<NotificacionDto> notificaciones;
}