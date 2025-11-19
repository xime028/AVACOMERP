package com.avacom.erp.modules.serviciotecnico.service;

import com.avacom.erp.modules.serviciotecnico.dto.CrearNotificacionRequest;
import com.avacom.erp.modules.serviciotecnico.dto.NotificacionDto;

import java.util.List;

public interface NotificacionService {

    NotificacionDto crearParaOrden(Long idOrden, CrearNotificacionRequest request);

    List<NotificacionDto> listarPorOrden(Long idOrden);

    List<NotificacionDto> listarPorUsuario(Long idUsuario);
}