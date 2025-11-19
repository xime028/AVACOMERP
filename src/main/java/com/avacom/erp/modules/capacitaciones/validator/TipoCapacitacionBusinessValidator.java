package com.avacom.erp.modules.capacitaciones.validator;

import com.avacom.erp.modules.capacitaciones.dto.ActualizarTipoCapacitacionRequest;
import com.avacom.erp.modules.capacitaciones.dto.CrearTipoCapacitacionRequest;
import com.avacom.erp.modules.capacitaciones.entity.TipoCapacitacionEntity;
import com.avacom.erp.modules.capacitaciones.repository.TipoCapacitacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TipoCapacitacionBusinessValidator {

    private final TipoCapacitacionRepository tipoCapacitacionRepository;

    public void validarCrear(CrearTipoCapacitacionRequest request) {
        if (tipoCapacitacionRepository.existsByCodigoTipo(request.getCodigoTipo())) {
            throw new IllegalArgumentException("Ya existe un tipo de capacitación con ese código");
        }
    }

    public void validarActualizar(TipoCapacitacionEntity existente, ActualizarTipoCapacitacionRequest request) {
        if (request.getCodigoTipo() != null &&
                !request.getCodigoTipo().equals(existente.getCodigoTipo()) &&
                tipoCapacitacionRepository.existsByCodigoTipo(request.getCodigoTipo())) {
            throw new IllegalArgumentException("Ya existe un tipo de capacitación con ese código");
        }
    }
}