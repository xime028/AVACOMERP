package com.avacom.erp.modules.almacen.service;

import com.avacom.erp.modules.almacen.dto.*;

import java.util.List;

public interface ProductoService {

    // Usado por tu API REST
    List<ProductoDto> listarTodos();

    List<ProductoDto> buscarPorFiltro(ProductoFiltroRequest filtro);

    // Usado por la vista / búsquedas
    List<ProductoDto> buscarPorTexto(String texto);

    ProductoDto crear(CrearProductoRequest request);

    // Para obtener datos básicos (lista, edición, etc.)
    ProductoDto obtenerPorId(Long id);

    // Para el detalle REST (ProductoController)
    ProductoDetalleDto obtenerDetalle(Long id);

    // Actualizar (REST y vista)
    ProductoDto actualizar(Long id, ActualizarProductoRequest request);

    void eliminar(Long id);
}