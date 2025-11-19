package com.avacom.erp.modules.serviciotecnico.validator;

import com.avacom.erp.modules.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificacionBusinessValidator {

    private final UsuarioRepository usuarioRepository;

    public void validarCrear(Long idUsuario) {
        if (idUsuario != null && !usuarioRepository.existsById(idUsuario)) {
            throw new IllegalArgumentException("Usuario notificación no existe");
        }
    }
}