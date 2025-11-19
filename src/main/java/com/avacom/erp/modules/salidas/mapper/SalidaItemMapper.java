package com.avacom.erp.modules.salidas.mapper;

import com.avacom.erp.modules.salidas.dto.SalidaItemDto;
import com.avacom.erp.modules.salidas.entity.SalidaItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SalidaSerialMapper.class})
public interface SalidaItemMapper {

    @Mapping(target = "idProducto", source = "producto.id")
    @Mapping(target = "nombreProducto", source = "producto.nombre")
    @Mapping(target = "seriales", source = "seriales")
    SalidaItemDto toDto(SalidaItemEntity entity);
}