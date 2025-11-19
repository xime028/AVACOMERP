package com.avacom.erp.modules.salidas.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalidaSerialDto {

    private Long id;
    private Long idSerial;
    private String numeroSerial;
}