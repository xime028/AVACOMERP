package com.avacom.erp.modules.usuarios.mapper;

import com.avacom.erp.modules.usuarios.dto.UsuarioDto;
import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "idRol", source = "rol.id")
    @Mapping(target = "nombreRol", source = "rol.nombre")
    UsuarioDto toDto(UsuarioEntity entity);
}