package com.avacom.erp.modules.garantias.mapper;

import com.avacom.erp.modules.garantias.dto.GarantiaDto;
import com.avacom.erp.modules.garantias.entity.GarantiaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GarantiaMapper {

    @Mapping(target = "idSerial", source = "serial.id")
    @Mapping(target = "numeroSerial", source = "serial.numeroSerial")
    @Mapping(target = "idCliente", source = "cliente.id")
    @Mapping(target = "nombreCliente", source = "cliente.nombre")
    @Mapping(target = "idProveedor", source = "proveedor.id")
    @Mapping(target = "nombreProveedor", source = "proveedor.nombre")
    @Mapping(target = "idOrdenServicio", source = "ordenServicio.id")
    @Mapping(target = "consecutivoOrdenServicio", source = "ordenServicio.consecutivo")
    @Mapping(target = "idArchivo", source = "archivo.id")
        // @Mapping(target = "idUsuario", source = "usuario.id")
    GarantiaDto toDto(GarantiaEntity entity);
}