package com.avacom.erp.modules.configuracion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarEmpresaRequest {

    @NotBlank
    @Size(max = 150)
    private String nombre;

    @NotBlank
    @Size(max = 20)
    private String nit;

    private String direccion;

    @Size(max = 50)
    private String telefono;

    @Size(max = 100)
    private String correo;

    /**
     * Id de archivo en tabla archivos (logo).
     */
    private Long idLogoArchivo;
}