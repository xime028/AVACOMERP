package com.avacom.erp.modules.entradas.service;

import com.avacom.erp.modules.entradas.dto.*;

import java.util.List;

public interface EntradaService {

    EntradaDto crear(CrearEntradaRequest request);

    EntradaDto actualizar(Long id, ActualizarEntradaRequest request);

    EntradaDetalleDto obtenerDetalle(Long id);

    List<EntradaDto> listarTodos();

    List<EntradaDto> buscarPorFiltro(EntradaFiltroRequest filtro);

    void eliminar(Long id);
}