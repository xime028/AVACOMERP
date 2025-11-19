package com.avacom.erp.modules.almacen.mapper;

import com.avacom.erp.modules.almacen.dto.SerialDto;
import com.avacom.erp.modules.almacen.entity.SerialEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SerialMapper {

    @Mapping(target = "idProducto", source = "producto.id")
    @Mapping(target = "referenciaProducto", source = "producto.referencia")
    SerialDto toDto(SerialEntity entity);
}