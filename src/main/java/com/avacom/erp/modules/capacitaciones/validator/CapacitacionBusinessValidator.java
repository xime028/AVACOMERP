package com.avacom.erp.modules.capacitaciones.validator;

import com.avacom.erp.modules.capacitaciones.dto.ActualizarCapacitacionRequest;
import com.avacom.erp.modules.capacitaciones.dto.CrearCapacitacionRequest;
import com.avacom.erp.modules.capacitaciones.entity.CapacitacionEntity;
import com.avacom.erp.modules.capacitaciones.repository.TipoCapacitacionRepository;
import com.avacom.erp.modules.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CapacitacionBusinessValidator {

    private final TipoCapacitacionRepository tipoCapacitacionRepository;
    private final UsuarioRepository usuarioRepository;

    public void validarCrear(CrearCapacitacionRequest request) {
        if (!tipoCapacitacionRepository.existsById(request.getIdTipoCapacitacion())) {
            throw new IllegalArgumentException("Tipo de capacitación no existe");
        }
        if (!usuarioRepository.existsById(request.getIdUsuario())) {
            throw new IllegalArgumentException("Usuario responsable no existe");
        }
    }

    public void validarActualizar(CapacitacionEntity existente, ActualizarCapacitacionRequest request) {
        if (request.getIdTipoCapacitacion() != null &&
                !tipoCapacitacionRepository.existsById(request.getIdTipoCapacitacion())) {
            throw new IllegalArgumentException("Tipo de capacitación no existe");
        }
        if (request.getIdUsuario() != null &&
                !usuarioRepository.existsById(request.getIdUsuario())) {
            throw new IllegalArgumentException("Usuario responsable no existe");
        }
    }
}