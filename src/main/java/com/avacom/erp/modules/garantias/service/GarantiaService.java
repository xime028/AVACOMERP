package com.avacom.erp.modules.garantias.service;

import com.avacom.erp.modules.garantias.dto.*;

import java.util.List;

public interface GarantiaService {

    GarantiaDto crear(CrearGarantiaRequest request);

    GarantiaDto actualizar(Long id, ActualizarGarantiaRequest request);

    GarantiaDetalleDto obtenerDetalle(Long id);

    List<GarantiaDto> listar();

    List<GarantiaDto> buscar(GarantiaFiltroRequest filtro);

    void eliminar(Long id);
}
