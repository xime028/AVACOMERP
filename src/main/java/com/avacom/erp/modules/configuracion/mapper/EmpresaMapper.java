package com.avacom.erp.modules.configuracion.mapper;

import com.avacom.erp.modules.configuracion.dto.EmpresaDto;
import com.avacom.erp.modules.configuracion.entity.EmpresaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmpresaMapper {

    EmpresaDto toDto(EmpresaEntity entity);
}