package com.avacom.erp.modules.salidas.service;

import com.avacom.erp.modules.salidas.dto.*;

import java.util.List;

public interface SalidaService {

    SalidaDto crear(CrearSalidaRequest request);

    SalidaDto actualizar(Long id, ActualizarSalidaRequest request);

    SalidaDetalleDto obtenerDetalle(Long id);

    List<SalidaDto> listarTodos();

    List<SalidaDto> buscarPorFiltro(SalidaFiltroRequest filtro);

    void eliminar(Long id);
}