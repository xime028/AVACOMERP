package com.avacom.erp.modules.auditoria.mapper;

import com.avacom.erp.modules.auditoria.dto.AuditoriaDetalleDto;
import com.avacom.erp.modules.auditoria.entity.AuditoriaDetalleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuditoriaDetalleMapper {

    AuditoriaDetalleDto toDto(AuditoriaDetalleEntity entity);
}