package com.avacom.erp.modules.salidas.mapper;

import com.avacom.erp.modules.salidas.dto.SalidaSerialDto;
import com.avacom.erp.modules.salidas.entity.SalidaSerialEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SalidaSerialMapper {

    @Mapping(target = "idSerial", source = "serial.id")
    @Mapping(target = "numeroSerial", source = "serial.numeroSerial")
    SalidaSerialDto toDto(SalidaSerialEntity entity);
}