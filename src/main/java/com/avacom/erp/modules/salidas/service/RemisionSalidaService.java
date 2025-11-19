package com.avacom.erp.modules.salidas.service;

import com.avacom.erp.modules.salidas.dto.CrearRemisionSalidaRequest;
import com.avacom.erp.modules.salidas.dto.RemisionSalidaDto;

import java.util.List;

public interface RemisionSalidaService {

    RemisionSalidaDto agregarRemision(Long idSalida, CrearRemisionSalidaRequest request);

    List<RemisionSalidaDto> listarPorSalida(Long idSalida);
}