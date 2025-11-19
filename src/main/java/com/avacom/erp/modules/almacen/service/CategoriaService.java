package com.avacom.erp.modules.almacen.service;

import com.avacom.erp.modules.almacen.dto.ActualizarCategoriaRequest;
import com.avacom.erp.modules.almacen.dto.CategoriaDto;

import java.util.List;

public interface CategoriaService {

    CategoriaDto crear(CategoriaDto dto);

    CategoriaDto actualizar(Long id, ActualizarCategoriaRequest request);

    List<CategoriaDto> listarTodos();

    CategoriaDto obtenerPorId(Long id);



    void eliminar(Long id);
}