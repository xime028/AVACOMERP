package com.avacom.erp.modules.usuarios.mapper;

import com.avacom.erp.modules.usuarios.dto.RolDto;
import com.avacom.erp.modules.usuarios.entity.RolEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolMapper {

    RolDto toDto(RolEntity entity);

    RolEntity toEntity(RolDto dto);
}