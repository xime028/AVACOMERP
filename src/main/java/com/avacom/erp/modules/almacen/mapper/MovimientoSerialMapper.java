package com.avacom.erp.modules.almacen.mapper;

import com.avacom.erp.modules.almacen.dto.MovimientoSerialDto;
import com.avacom.erp.modules.almacen.entity.MovimientoSerialEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovimientoSerialMapper {

    @Mapping(target = "idSerial", source = "serial.id")
    @Mapping(target = "numeroSerial", source = "serial.numeroSerial")
    @Mapping(target = "idUsuario", source = "usuario.id")
    @Mapping(target = "nombreUsuario", source = "usuario.nombre")
    MovimientoSerialDto toDto(MovimientoSerialEntity entity);
}