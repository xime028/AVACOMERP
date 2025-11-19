package com.avacom.erp.modules.configuracion.service;

import com.avacom.erp.modules.configuracion.dto.ActualizarEmpresaRequest;
import com.avacom.erp.modules.configuracion.dto.EmpresaDto;

public interface EmpresaConfigService {

    /**
     * Retorna la configuración actual de la empresa.
     * Si no existe, crea un registro por defecto.
     */
    EmpresaDto obtener();

    /**
     * Actualiza la configuración de la empresa.
     */
    EmpresaDto actualizar(ActualizarEmpresaRequest request);
}