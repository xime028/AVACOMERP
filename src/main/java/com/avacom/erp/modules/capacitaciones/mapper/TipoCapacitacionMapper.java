package com.avacom.erp.modules.capacitaciones.mapper;

import com.avacom.erp.modules.capacitaciones.dto.TipoCapacitacionDto;
import com.avacom.erp.modules.capacitaciones.entity.TipoCapacitacionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoCapacitacionMapper {

    TipoCapacitacionDto toDto(TipoCapacitacionEntity entity);
}