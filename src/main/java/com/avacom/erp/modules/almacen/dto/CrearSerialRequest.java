package com.avacom.erp.modules.almacen.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrearSerialRequest {

    @NotBlank
    @Size(max = 150)
    private String numeroSerial;

    @NotBlank
    @Size(max = 30)
    private String estado;

    @NotNull
    private Long idProducto;
}