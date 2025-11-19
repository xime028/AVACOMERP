package com.avacom.erp.modules.almacen.mapper;

import com.avacom.erp.modules.almacen.dto.CategoriaDto;
import com.avacom.erp.modules.almacen.entity.CategoriaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaDto toDto(CategoriaEntity entity);

    CategoriaEntity toEntity(CategoriaDto dto);
}