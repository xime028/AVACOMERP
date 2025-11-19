package com.avacom.erp.modules.entradas.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntradaSerialDto {

    private Long id;
    private Long idSerial;
    private String numeroSerial;
}