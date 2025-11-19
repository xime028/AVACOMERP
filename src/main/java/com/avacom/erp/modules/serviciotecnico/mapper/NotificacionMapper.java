package com.avacom.erp.modules.serviciotecnico.mapper;

import com.avacom.erp.modules.serviciotecnico.dto.NotificacionDto;
import com.avacom.erp.modules.serviciotecnico.entity.NotificacionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificacionMapper {

    @Mapping(target = "idOrdenServicio", source = "ordenServicio.id")
    @Mapping(target = "idUsuario", source = "usuario.id")
    @Mapping(target = "nombreUsuario", source = "usuario.nombre")
    NotificacionDto toDto(NotificacionEntity entity);
}