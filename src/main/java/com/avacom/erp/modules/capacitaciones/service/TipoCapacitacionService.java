package com.avacom.erp.modules.capacitaciones.service;

import com.avacom.erp.modules.capacitaciones.dto.*;

import java.util.List;

public interface TipoCapacitacionService {

    TipoCapacitacionDto crear(CrearTipoCapacitacionRequest request);

    TipoCapacitacionDto actualizar(Long id, ActualizarTipoCapacitacionRequest request);

    TipoCapacitacionDto obtener(Long id);

    List<TipoCapacitacionDto> listar();

    void eliminar(Long id);
}