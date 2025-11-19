package com.avacom.erp.modules.serviciotecnico.service;

import com.avacom.erp.modules.serviciotecnico.dto.CrearObservacionRequest;
import com.avacom.erp.modules.serviciotecnico.dto.ObservacionDto;

import java.util.List;

public interface ObservacionOrdenService {

    ObservacionDto agregarObservacion(Long idOrden, CrearObservacionRequest request);

    List<ObservacionDto> listarPorOrden(Long idOrden);
}