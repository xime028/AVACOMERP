package com.avacom.erp.modules.usuarios.validator;

import com.avacom.erp.modules.usuarios.dto.ActualizarUsuarioRequest;
import com.avacom.erp.modules.usuarios.dto.CrearUsuarioRequest;
import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import com.avacom.erp.modules.usuarios.repository.RolRepository;
import com.avacom.erp.modules.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioBusinessValidator {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public void validarCrear(CrearUsuarioRequest request) {
        if (usuarioRepository.existsByUsuario(request.getUsuario())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese nombre de usuario");
        }
        if (usuarioRepository.existsByCorreo(request.getCorreo())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese correo");
        }
        if (request.getIdRol() == null ||
                !rolRepository.existsById(request.getIdRol())) {
            throw new IllegalArgumentException("El rol especificado no existe");
        }
    }

    public void validarActualizar(UsuarioEntity existente, ActualizarUsuarioRequest request) {
        if (request.getCorreo() != null &&
                !request.getCorreo().equalsIgnoreCase(existente.getCorreo()) &&
                usuarioRepository.existsByCorreo(request.getCorreo())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese correo");
        }

        if (request.getIdRol() != null &&
                !rolRepository.existsById(request.getIdRol())) {
            throw new IllegalArgumentException("El rol especificado no existe");
        }
    }
}