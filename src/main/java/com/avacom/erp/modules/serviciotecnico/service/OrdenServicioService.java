package com.avacom.erp.modules.serviciotecnico.service;

import com.avacom.erp.modules.serviciotecnico.dto.*;

import java.util.List;

public interface OrdenServicioService {

    OrdenServicioDto crear(CrearOrdenServicioRequest request);

    OrdenServicioDto actualizar(Long id, ActualizarOrdenServicioRequest request);

    OrdenServicioDetalleDto obtenerDetalle(Long id);

    List<OrdenServicioDto> listarTodos();

    List<OrdenServicioDto> buscarPorFiltro(OrdenServicioFiltroRequest filtro);

    void eliminar(Long id);
}