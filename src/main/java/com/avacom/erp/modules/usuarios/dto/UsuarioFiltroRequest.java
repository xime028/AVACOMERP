package com.avacom.erp.modules.usuarios.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioFiltroRequest {

    private String nombre;
    private String usuario;
    private String correo;
    private String estado;
    private Long idRol;
}