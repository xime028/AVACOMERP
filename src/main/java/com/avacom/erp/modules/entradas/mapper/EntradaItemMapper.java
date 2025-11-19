package com.avacom.erp.modules.entradas.mapper;

import com.avacom.erp.modules.entradas.dto.EntradaItemDto;
import com.avacom.erp.modules.entradas.entity.EntradaItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EntradaSerialMapper.class})
public interface EntradaItemMapper {

    @Mapping(target = "idProducto", source = "producto.id")
    @Mapping(target = "nombreProducto", source = "producto.nombre")
    @Mapping(target = "seriales", source = "seriales")
    EntradaItemDto toDto(EntradaItemEntity entity);
}