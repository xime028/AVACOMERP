package com.avacom.erp.modules.clientes.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarClienteRequest {

    @Size(max = 100)
    private String nombre;

    @Email
    @Size(max = 100)
    private String correo;

    @Size(max = 20)
    private String telefono;

    @Size(max = 100)
    private String direccion;

    @Size(max = 50)
    private String tipoCliente;

    private Long idGrupo;

    private Long idArchivo;

    private Boolean estado;
}