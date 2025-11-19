package com.avacom.erp.modules.serviciotecnico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrearOrdenServicioRequest {

    @NotNull
    private Long idCliente;

    private Long idTecnico;

    private Long idSerial;

    private Long idFalla;

    private Long idSolucion;

    private Long idArchivo;

    @NotBlank
    @Size(max = 200)
    private String descripcionDano;

    @Size(max = 50)
    private String prioridad;

    @Size(max = 30)
    private String estado; // si null → “ABIERTA”

    private String observacionesGenerales;
}