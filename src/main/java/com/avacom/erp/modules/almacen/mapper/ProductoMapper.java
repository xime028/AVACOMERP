package com.avacom.erp.modules.almacen.mapper;

import com.avacom.erp.modules.almacen.dto.ProductoDetalleDto;
import com.avacom.erp.modules.almacen.dto.ProductoDto;
import com.avacom.erp.modules.almacen.entity.ProductoEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {SerialMapper.class})
public interface ProductoMapper {

    @Mapping(target = "idCategoria", source = "categoria.id")
    @Mapping(target = "nombreCategoria", source = "categoria.nombre")
    ProductoDto toDto(ProductoEntity entity);

    @Mapping(target = "idCategoria", source = "categoria.id")
    @Mapping(target = "nombreCategoria", source = "categoria.nombre")
    @Mapping(target = "seriales", source = "seriales")
    ProductoDetalleDto toDetalleDto(ProductoEntity entity);
}