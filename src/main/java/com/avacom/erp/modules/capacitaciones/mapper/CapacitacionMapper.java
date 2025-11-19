package com.avacom.erp.modules.capacitaciones.mapper;

import com.avacom.erp.modules.capacitaciones.dto.CapacitacionDto;
import com.avacom.erp.modules.capacitaciones.entity.CapacitacionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CapacitacionMapper {

    @Mapping(target = "idTipoCapacitacion", source = "tipoCapacitacion.id")
    @Mapping(target = "codigoTipo", source = "tipoCapacitacion.codigoTipo")
    @Mapping(target = "tema", source = "tipoCapacitacion.tema")
    @Mapping(target = "idUsuario", source = "usuario.id")
    @Mapping(target = "nombreUsuario", source = "usuario.nombre")
    CapacitacionDto toDto(CapacitacionEntity entity);
}