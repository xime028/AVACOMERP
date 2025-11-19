package com.avacom.erp.modules.clientes.service;

import com.avacom.erp.modules.clientes.dto.GrupoDto;
import com.avacom.erp.modules.clientes.entity.GrupoEntity;
import com.avacom.erp.modules.clientes.mapper.GrupoMapper;
import com.avacom.erp.modules.clientes.repository.GrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GrupoServiceImpl implements GrupoService {

    private final GrupoRepository grupoRepository;
    private final GrupoMapper grupoMapper;

    @Override
    public GrupoDto crear(GrupoDto dto) {
        if (grupoRepository.existsByNombre(dto.getNombre())) {
            throw new IllegalArgumentException("Ya existe un grupo con ese nombre");
        }
        GrupoEntity entity = grupoMapper.toEntity(dto);
        GrupoEntity guardado = grupoRepository.save(entity);
        return grupoMapper.toDto(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GrupoDto> listarTodos() {
        return grupoRepository.findAll()
                .stream()
                .map(grupoMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public GrupoDto obtenerPorId(Long id) {
        GrupoEntity entity = grupoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Grupo no encontrado"));
        return grupoMapper.toDto(entity);
    }

    @Override
    public void eliminar(Long id) {
        GrupoEntity entity = grupoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Grupo no encontrado"));
        if (entity.getClientes() != null && !entity.getClientes().isEmpty()) {
            throw new IllegalStateException("No se puede eliminar un grupo con clientes asociados");
        }
        grupoRepository.delete(entity);
    }
}