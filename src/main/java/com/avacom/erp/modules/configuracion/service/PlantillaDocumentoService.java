package com.avacom.erp.modules.configuracion.service;

import com.avacom.erp.modules.configuracion.dto.ActualizarPlantillaDocumentoRequest;
import com.avacom.erp.modules.configuracion.dto.PlantillaDocumentoDto;

import java.util.List;

public interface PlantillaDocumentoService {

    List<PlantillaDocumentoDto> listarTodas();

    PlantillaDocumentoDto obtenerPorCodigo(String codigo);

    PlantillaDocumentoDto actualizar(String codigo, ActualizarPlantillaDocumentoRequest request);
}