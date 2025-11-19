package com.avacom.erp.modules.clientes.service;

import com.avacom.erp.modules.clientes.dto.GrupoDto;

import java.util.List;

public interface GrupoService {

    GrupoDto crear(GrupoDto dto);

    List<GrupoDto> listarTodos();

    GrupoDto obtenerPorId(Long id);

    void eliminar(Long id);
}