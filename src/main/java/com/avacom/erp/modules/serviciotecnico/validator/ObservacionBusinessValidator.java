package com.avacom.erp.modules.serviciotecnico.validator;

import com.avacom.erp.modules.serviciotecnico.entity.OrdenServicioEntity;
import org.springframework.stereotype.Component;

@Component
public class ObservacionBusinessValidator {

    public void validarAgregar(OrdenServicioEntity orden) {
        if (orden == null) {
            throw new IllegalArgumentException("Orden de servicio no encontrada");
        }
    }
}