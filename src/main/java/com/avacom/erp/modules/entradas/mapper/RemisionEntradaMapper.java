package com.avacom.erp.modules.entradas.mapper;

import com.avacom.erp.modules.entradas.dto.RemisionEntradaDto;
import com.avacom.erp.modules.entradas.entity.RemisionEntradaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RemisionEntradaMapper {

    @Mapping(target = "idEntrada", source = "entrada.id")
    RemisionEntradaDto toDto(RemisionEntradaEntity entity);
}