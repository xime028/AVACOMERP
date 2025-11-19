package com.avacom.erp.modules.capacitaciones.service;

import com.avacom.erp.modules.capacitaciones.dto.*;

import java.util.List;

public interface CapacitacionService {

    CapacitacionDto crear(CrearCapacitacionRequest request);

    CapacitacionDto actualizar(Long id, ActualizarCapacitacionRequest request);

    CapacitacionDetalleDto obtenerDetalle(Long id);

    List<CapacitacionDto> listar();

    List<CapacitacionDto> buscarPorFiltro(CapacitacionFiltroRequest filtro);

    void eliminar(Long id);
}