package com.avacom.erp.modules.serviciotecnico.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarOrdenServicioRequest {

    @Size(max = 200)
    private String descripcionDano;

    @Size(max = 50)
    private String prioridad;

    @Size(max = 30)
    private String estado;

    private String observacionesGenerales;

    private Long idCliente;

    private Long idTecnico;

    private Long idSerial;

    private Long idFalla;

    private Long idSolucion;

    private Long idArchivo;
}