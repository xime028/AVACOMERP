package com.avacom.erp.modules.configuracion.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpresaDto {

    private Long id;
    private String nombre;
    private String nit;
    private String direccion;
    private String telefono;
    private String correo;
    private Long idLogoArchivo;
    private OffsetDateTime fechaRegistro;
}