package com.avacom.erp.modules.configuracion.validator;

import com.avacom.erp.modules.configuracion.dto.ActualizarEmpresaRequest;
import com.avacom.erp.modules.configuracion.entity.EmpresaEntity;
import com.avacom.erp.modules.configuracion.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmpresaBusinessValidator {

    private final EmpresaRepository empresaRepository;

    public void validarActualizar(EmpresaEntity existente, ActualizarEmpresaRequest request) {
        // Simple: si el NIT cambia, podríamos validar duplicados
        if (!existente.getNit().equals(request.getNit())
                && empresaRepository.existsByNit(request.getNit())) {
            throw new IllegalArgumentException("Ya existe una empresa con ese NIT");
        }
    }
}