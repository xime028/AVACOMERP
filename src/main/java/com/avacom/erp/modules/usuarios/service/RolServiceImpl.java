package com.avacom.erp.modules.usuarios.service;

import com.avacom.erp.modules.usuarios.dto.RolDto;
import com.avacom.erp.modules.usuarios.entity.RolEntity;
import com.avacom.erp.modules.usuarios.mapper.RolMapper;
import com.avacom.erp.modules.usuarios.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;
    private final RolMapper rolMapper;

    @Override
    @Transactional(readOnly = true)
    public List<RolDto> listarTodos() {
        return rolRepository.findAll()
                .stream()
                .map(rolMapper::toDto)
                .toList();
    }

    @Override
    public RolDto crear(RolDto dto) {
        if (rolRepository.existsByNombre(dto.getNombre())) {
            throw new IllegalArgumentException("Ya existe un rol con ese nombre");
        }
        RolEntity entity = rolMapper.toEntity(dto);
        RolEntity guardado = rolRepository.save(entity);
        return rolMapper.toDto(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public RolDto obtenerPorId(Long id) {
        RolEntity entity = rolRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado"));
        return rolMapper.toDto(entity);
    }

    @Override
    public RolDto actualizar(Long id, RolDto dto) {
        RolEntity entity = rolRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado"));

        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());

        RolEntity actualizado = rolRepository.save(entity);
        return rolMapper.toDto(actualizado);
    }

    @Override
    public void eliminar(Long id) {
        if (!rolRepository.existsById(id)) {
            throw new IllegalArgumentException("Rol no encontrado");
        }
        rolRepository.deleteById(id);
    }
}