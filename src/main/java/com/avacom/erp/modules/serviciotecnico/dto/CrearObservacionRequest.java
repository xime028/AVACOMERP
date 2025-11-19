package com.avacom.erp.modules.serviciotecnico.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrearObservacionRequest {

    @NotBlank
    private String observacion;

    private Long idArchivo;
}