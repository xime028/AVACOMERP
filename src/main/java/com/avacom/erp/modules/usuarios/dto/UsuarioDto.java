package com.avacom.erp.modules.usuarios.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDto {

    private Long id;
    private String nombre;
    private String usuario;
    private String correo;
    private String telefono;
    private String estado;
    private Long idRol;
    private String nombreRol;
    private Long idArchivo;
    private OffsetDateTime fechaCreacion;
}