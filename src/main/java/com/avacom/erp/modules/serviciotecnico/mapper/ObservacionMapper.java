package com.avacom.erp.modules.serviciotecnico.mapper;

import com.avacom.erp.modules.serviciotecnico.dto.ObservacionDto;
import com.avacom.erp.modules.serviciotecnico.entity.ObservacionOrdenEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ObservacionMapper {

    @Mapping(target = "idOrdenServicio", source = "ordenServicio.id")
    ObservacionDto toDto(ObservacionOrdenEntity entity);
}