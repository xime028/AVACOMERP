package com.avacom.erp.modules.auditoria.mapper;

import com.avacom.erp.modules.auditoria.dto.AuditoriaEventoDto;
import com.avacom.erp.modules.auditoria.entity.AuditoriaEventoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuditoriaEventoMapper {

    @Mapping(target = "idUsuario", source = "usuario.id")
    @Mapping(target = "nombreUsuario", source = "usuario.nombre")
    AuditoriaEventoDto toDto(AuditoriaEventoEntity entity);
}