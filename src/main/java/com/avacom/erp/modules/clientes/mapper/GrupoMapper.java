package com.avacom.erp.modules.clientes.mapper;

import com.avacom.erp.modules.clientes.dto.GrupoDto;
import com.avacom.erp.modules.clientes.entity.GrupoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GrupoMapper {

    GrupoDto toDto(GrupoEntity entity);

    GrupoEntity toEntity(GrupoDto dto);
}