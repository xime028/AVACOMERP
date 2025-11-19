package com.avacom.erp.modules.serviciotecnico.mapper;

import com.avacom.erp.modules.serviciotecnico.dto.OrdenServicioDto;
import com.avacom.erp.modules.serviciotecnico.entity.OrdenServicioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrdenServicioMapper {

    @Mapping(target = "idCliente", source = "cliente.id")
    @Mapping(target = "nombreCliente", source = "cliente.nombre")
    @Mapping(target = "idTecnico", source = "tecnico.id")
    @Mapping(target = "nombreTecnico", source = "tecnico.nombre")
    @Mapping(target = "idSerial", source = "serial.id")
    @Mapping(target = "numeroSerial", source = "serial.numeroSerial")
    OrdenServicioDto toDto(OrdenServicioEntity entity);
}