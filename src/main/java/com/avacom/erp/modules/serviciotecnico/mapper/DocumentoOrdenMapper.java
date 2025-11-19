package com.avacom.erp.modules.serviciotecnico.mapper;

import com.avacom.erp.modules.serviciotecnico.dto.DocumentoOrdenDto;
import com.avacom.erp.modules.serviciotecnico.entity.DocumentoOrdenEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocumentoOrdenMapper {

    @Mapping(target = "idOrdenServicio", source = "ordenServicio.id")
    DocumentoOrdenDto toDto(DocumentoOrdenEntity entity);
}