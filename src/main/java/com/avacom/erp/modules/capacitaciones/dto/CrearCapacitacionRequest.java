package com.avacom.erp.modules.capacitaciones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrearCapacitacionRequest {

    @NotBlank
    @Size(max = 100)
    private String titulo;

    /**
     * Si es null, se usa la fecha actual.
     */
    private OffsetDateTime fecha;

    private Integer numeroHoras;

    private Integer numeroAsistentes;

    @NotNull
    private Long idTipoCapacitacion;

    @NotNull
    private Long idUsuario;

    /**
     * Archivo asociado: acta, lista, etc.
     */
    private Long idArchivo;
}