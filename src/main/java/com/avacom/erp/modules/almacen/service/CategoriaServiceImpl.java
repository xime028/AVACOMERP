package com.avacom.erp.modules.almacen.service;

import com.avacom.erp.modules.almacen.dto.ActualizarCategoriaRequest;
import com.avacom.erp.modules.almacen.dto.CategoriaDto;
import com.avacom.erp.modules.almacen.dto.CrearCategoriaRequest;
import com.avacom.erp.modules.almacen.entity.CategoriaEntity;
import com.avacom.erp.modules.almacen.mapper.CategoriaMapper;
import com.avacom.erp.modules.almacen.repository.CategoriaRepository;
import com.avacom.erp.modules.almacen.validator.CategoriaBusinessValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;
    private final CategoriaBusinessValidator validator;

    @Override
    public CategoriaDto crear(CategoriaDto categoriaDto) {
        if (categoriaDto == null) {
            throw new IllegalArgumentException("La categoría es obligatoria");
        }
        if (categoriaDto.getNombre() == null || categoriaDto.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre de la categoría es obligatorio");
        }

        // Validar que no exista otra categoría con el mismo nombre
        if (categoriaRepository.existsByNombreIgnoreCase(categoriaDto.getNombre())) {
            throw new IllegalArgumentException("Ya existe una categoría con ese nombre");
        }

        CategoriaEntity entity = CategoriaEntity.builder()
                .nombre(categoriaDto.getNombre())
                .descripcion(categoriaDto.getDescripcion())
                .build();

        CategoriaEntity guardada = categoriaRepository.save(entity);
        return categoriaMapper.toDto(guardada);
    }

    @Override
    public CategoriaDto actualizar(Long id, ActualizarCategoriaRequest request) {
        CategoriaEntity existente = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));

        validator.validarActualizar(existente, request);

        if (request.getNombre() != null) {
            existente.setNombre(request.getNombre());
        }
        if (request.getDescripcion() != null) {
            existente.setDescripcion(request.getDescripcion());
        }

        CategoriaEntity guardada = categoriaRepository.save(existente);
        return categoriaMapper.toDto(guardada);
    }

    @Transactional(readOnly = true)
    public CategoriaDto obtener(Long id) {
        CategoriaEntity entity = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
        return categoriaMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaDto obtenerPorId(Long id) {
        // Si ya tienes un método obtener(id) puedes reutilizarlo:
        return obtener(id);
    }

    @Transactional(readOnly = true)
    public List<CategoriaDto> listar() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaDto> listarTodos() {
        // Si ya tienes un método listar(), lo reutilizamos:
        return listar();
    }

    @Override
    public void eliminar(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new IllegalArgumentException("Categoría no encontrada");
        }
        categoriaRepository.deleteById(id);
    }
}