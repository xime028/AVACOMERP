package com.avacom.erp.modules.auditoria.service;

import com.avacom.erp.modules.auditoria.dto.*;

import java.util.List;

public interface AuditoriaService {

    /**
     * Registra un evento de auditoría con sus detalles (opcionalmente).
     */
    AuditoriaEventoDto registrar(RegistrarAuditoriaRequest request);

    /**
     * Obtiene un evento con detalles.
     */
    AuditoriaEventoDetalleDto obtenerDetalle(Long id);

    /**
     * Busca eventos por filtros.
     */
    List<AuditoriaEventoDto> buscar(AuditoriaFiltroRequest filtro);

    /**
     * Lista los últimos N eventos.
     */
    List<AuditoriaEventoDto> listarUltimos(int limite);
}