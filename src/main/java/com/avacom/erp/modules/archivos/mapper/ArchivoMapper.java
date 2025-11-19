package com.avacom.erp.modules.archivos.mapper;

import com.avacom.erp.modules.archivos.dto.ArchivoDto;
import com.avacom.erp.modules.archivos.entity.ArchivoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArchivoMapper {

    @Mapping(target = "idUsuarioCargador", source = "usuarioCargador.id")
    @Mapping(target = "nombreUsuarioCargador", source = "usuarioCargador.nombre")
    ArchivoDto toDto(ArchivoEntity entity);
}
