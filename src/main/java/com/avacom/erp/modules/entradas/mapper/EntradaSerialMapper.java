package com.avacom.erp.modules.entradas.mapper;

import com.avacom.erp.modules.entradas.dto.EntradaSerialDto;
import com.avacom.erp.modules.entradas.entity.EntradaSerialEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EntradaSerialMapper {

    @Mapping(target = "idSerial", source = "serial.id")
    @Mapping(target = "numeroSerial", source = "serial.numeroSerial")
    EntradaSerialDto toDto(EntradaSerialEntity entity);
}