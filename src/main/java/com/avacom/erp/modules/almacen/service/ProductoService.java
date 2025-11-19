package com.avacom.erp.modules.almacen.service;

import com.avacom.erp.modules.almacen.dto.*;

import java.util.List;

public interface ProductoService {

    ProductoDto crear(CrearProductoRequest request);

    ProductoDto actualizar(Long id, ActualizarProductoRequest request);

    ProductoDetalleDto obtenerDetalle(Long id);

    List<ProductoDto> listarTodos();

    List<ProductoDto> buscarPorFiltro(ProductoFiltroRequest filtro);

    void eliminar(Long id);
}