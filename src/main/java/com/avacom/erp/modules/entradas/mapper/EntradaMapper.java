package com.avacom.erp.modules.entradas.mapper;

import com.avacom.erp.modules.entradas.dto.EntradaDto;
import com.avacom.erp.modules.entradas.entity.EntradaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EntradaMapper {

    @Mapping(target = "idProveedor", source = "proveedor.id")
    @Mapping(target = "nombreProveedor", source = "proveedor.nombre")
    @Mapping(target = "idUsuario", source = "usuario.id")
    @Mapping(target = "nombreUsuario", source = "usuario.nombre")
    @Mapping(target = "totalItems",
            expression = "java(entity.getItems() != null ? entity.getItems().stream().mapToInt(i -> i.getCantidad() != null ? i.getCantidad() : 0).sum() : 0)")
    EntradaDto toDto(EntradaEntity entity);
}