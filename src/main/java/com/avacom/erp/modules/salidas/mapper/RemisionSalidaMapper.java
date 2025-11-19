package com.avacom.erp.modules.salidas.mapper;

import com.avacom.erp.modules.salidas.dto.RemisionSalidaDto;
import com.avacom.erp.modules.salidas.entity.RemisionSalidaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RemisionSalidaMapper {

    @Mapping(target = "idSalida", source = "salida.id")
    RemisionSalidaDto toDto(RemisionSalidaEntity entity);
}