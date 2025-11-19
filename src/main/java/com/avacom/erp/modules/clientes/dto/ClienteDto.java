package com.avacom.erp.modules.clientes.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDto {

    private Long id;
    private String identificacion;
    private String nombre;
    private String correo;
    private String telefono;
    private String direccion;
    private Boolean estado;
    private String tipoCliente;
    private Long idGrupo;
    private String nombreGrupo;
}