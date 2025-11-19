package com.avacom.erp.modules.salidas.mapper;

import com.avacom.erp.modules.salidas.dto.SalidaDto;
import com.avacom.erp.modules.salidas.entity.SalidaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SalidaMapper {

    @Mapping(target = "idCliente", source = "cliente.id")
    @Mapping(target = "nombreCliente", source = "cliente.nombre")
    @Mapping(target = "idUsuario", source = "usuario.id")
    @Mapping(target = "nombreUsuario", source = "usuario.nombre")
    @Mapping(target = "totalItems",
            expression = "java(entity.getItems() != null ? entity.getItems().stream().mapToInt(i -> i.getCantidad() != null ? i.getCantidad() : 0).sum() : 0)")
    SalidaDto toDto(SalidaEntity entity);
}