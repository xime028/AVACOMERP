package com.avacom.erp.modules.clientes.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteFiltroRequest {

    private String nombre;
    private String identificacion;
    private String tipoCliente;
    private Long idGrupo;
    private Boolean estado;
    private OffsetDateTime fechaDesde;
    private OffsetDateTime fechaHasta;
}