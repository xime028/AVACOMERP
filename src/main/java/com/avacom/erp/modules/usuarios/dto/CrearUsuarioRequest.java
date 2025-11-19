package com.avacom.erp.modules.usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrearUsuarioRequest {

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @NotBlank
    @Size(max = 100)
    private String usuario;

    @NotBlank
    @Email
    @Size(max = 100)
    private String correo;

    @Size(max = 30)
    private String telefono;

    @NotBlank
    @Size(min = 6, max = 100)
    private String contrasena;

    @NotBlank
    @Size(max = 20)
    private String estado;

    private Long idRol;

    private Long idArchivo;
}