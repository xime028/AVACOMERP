package com.avacom.erp.modules.analitica.validator;

import com.avacom.erp.modules.analitica.dto.AnaliticaFiltroFechaRequest;
import org.springframework.stereotype.Component;

@Component
public class AnaliticaFechaValidator {

    public void validarRango(AnaliticaFiltroFechaRequest filtro) {
        if (filtro == null) {
            return;
        }
        if (filtro.getFechaDesde() != null && filtro.getFechaHasta() != null &&
                filtro.getFechaDesde().isAfter(filtro.getFechaHasta())) {
            throw new IllegalArgumentException("La fechaDesde no puede ser mayor que fechaHasta");
        }
    }
}