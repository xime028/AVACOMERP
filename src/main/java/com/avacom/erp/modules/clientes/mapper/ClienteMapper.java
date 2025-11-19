package com.avacom.erp.modules.clientes.mapper;

import com.avacom.erp.modules.clientes.dto.ClienteDetalleDto;
import com.avacom.erp.modules.clientes.dto.ClienteDto;
import com.avacom.erp.modules.clientes.entity.ClienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "idGrupo", source = "grupo.id")
    @Mapping(target = "nombreGrupo", source = "grupo.nombre")
    ClienteDto toDto(ClienteEntity entity);

    @Mapping(target = "idGrupo", source = "grupo.id")
    @Mapping(target = "nombreGrupo", source = "grupo.nombre")
    ClienteDetalleDto toDetalleDto(ClienteEntity entity);
}