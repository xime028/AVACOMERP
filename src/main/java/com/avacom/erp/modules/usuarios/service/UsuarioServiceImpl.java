package com.avacom.erp.modules.usuarios.service;

import com.avacom.erp.modules.usuarios.dto.*;
import com.avacom.erp.modules.usuarios.entity.RolEntity;
import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import com.avacom.erp.modules.usuarios.mapper.UsuarioMapper;
import com.avacom.erp.modules.usuarios.repository.RolRepository;
import com.avacom.erp.modules.usuarios.repository.UsuarioRepository;
import com.avacom.erp.modules.usuarios.validator.UsuarioBusinessValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final UsuarioMapper usuarioMapper;
    private final UsuarioBusinessValidator usuarioValidator;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDto> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDto> buscarPorFiltro(UsuarioFiltroRequest filtro) {
        return usuarioRepository.findAll()
                .stream()
                .filter(u -> filtro.getNombre() == null ||
                        (u.getNombre() != null && u.getNombre().toLowerCase().contains(filtro.getNombre().toLowerCase())))
                .filter(u -> filtro.getUsuario() == null ||
                        Objects.equals(u.getUsuario(), filtro.getUsuario()))
                .filter(u -> filtro.getCorreo() == null ||
                        Objects.equals(u.getCorreo(), filtro.getCorreo()))
                .filter(u -> filtro.getEstado() == null ||
                        Objects.equals(u.getEstado(), filtro.getEstado()))
                .filter(u -> filtro.getIdRol() == null ||
                        (u.getRol() != null && Objects.equals(u.getRol().getId(), filtro.getIdRol())))
                .map(usuarioMapper::toDto)
                .toList();
    }

    @Override
    public UsuarioDto crear(CrearUsuarioRequest request) {
        usuarioValidator.validarCrear(request);

        RolEntity rol = rolRepository.findById(request.getIdRol())
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado"));

        String hash = passwordEncoder.encode(request.getContrasena());

        UsuarioEntity entity = UsuarioEntity.builder()
                .nombre(request.getNombre())
                .usuario(request.getUsuario())
                .correo(request.getCorreo())
                .telefono(request.getTelefono())
                .estado(request.getEstado())
                .rol(rol)
                .contrasenaHash(hash)
                .fechaCreacion(OffsetDateTime.now())
                .idArchivo(request.getIdArchivo())
                .build();

        UsuarioEntity guardado = usuarioRepository.save(entity);
        return usuarioMapper.toDto(guardado);
    }

    @Override
    public UsuarioDto actualizar(Long id, ActualizarUsuarioRequest request) {
        UsuarioEntity existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        usuarioValidator.validarActualizar(existente, request);

        if (request.getNombre() != null) {
            existente.setNombre(request.getNombre());
        }
        if (request.getCorreo() != null) {
            existente.setCorreo(request.getCorreo());
        }
        if (request.getTelefono() != null) {
            existente.setTelefono(request.getTelefono());
        }
        if (request.getEstado() != null) {
            existente.setEstado(request.getEstado());
        }
        if (request.getIdArchivo() != null) {
            existente.setIdArchivo(request.getIdArchivo());
        }
        if (request.getIdRol() != null) {
            RolEntity rol = rolRepository.findById(request.getIdRol())
                    .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado"));
            existente.setRol(rol);
        }
        if (request.getNuevaContrasena() != null && !request.getNuevaContrasena().isBlank()) {
            existente.setContrasenaHash(passwordEncoder.encode(request.getNuevaContrasena()));
        }

        UsuarioEntity actualizado = usuarioRepository.save(existente);
        return usuarioMapper.toDto(actualizado);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioDto obtenerPorId(Long id) {
        UsuarioEntity entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        return usuarioMapper.toDto(entity);
    }

    @Override
    public void eliminar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}