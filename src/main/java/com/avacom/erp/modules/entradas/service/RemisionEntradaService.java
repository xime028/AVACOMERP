package com.avacom.erp.modules.entradas.service;

import com.avacom.erp.modules.entradas.dto.CrearRemisionEntradaRequest;
import com.avacom.erp.modules.entradas.dto.RemisionEntradaDto;

import java.util.List;

public interface RemisionEntradaService {

    RemisionEntradaDto agregarRemision(Long idEntrada, CrearRemisionEntradaRequest request);

    List<RemisionEntradaDto> listarPorEntrada(Long idEntrada);
}