package com.avacom.erp.modules.almacen.service;

import com.avacom.erp.modules.almacen.dto.*;
import com.avacom.erp.modules.almacen.entity.CategoriaEntity;
import com.avacom.erp.modules.almacen.entity.ProductoEntity;
import com.avacom.erp.modules.almacen.mapper.ProductoMapper;
import com.avacom.erp.modules.almacen.repository.CategoriaRepository;
import com.avacom.erp.modules.almacen.repository.ProductoRepository;
import com.avacom.erp.modules.almacen.validator.ProductoBusinessValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoMapper productoMapper;
    private final ProductoBusinessValidator validator;

    @Override
    public List<ProductoDto> listarTodos() {
        List<ProductoEntity> entities = productoRepository.findAll();
        return entities.stream()
                .map(productoMapper::toDto)
                .toList();
    }

    @Override
    public List<ProductoDto> buscarPorTexto(String texto) {
        if (texto == null || texto.isBlank()) {
            return listarTodos();
        }
        List<ProductoEntity> entities =
                productoRepository.findByReferenciaContainingIgnoreCaseOrNombreContainingIgnoreCase(texto, texto);

        return entities.stream()
                .map(productoMapper::toDto)
                .toList();
    }

    @Override
    public List<ProductoDto> buscarPorFiltro(ProductoFiltroRequest filtro) {
        // Por ahora usamos una implementación simple para no depender
        // de los campos internos de ProductoFiltroRequest.
        // Más adelante, si quieres, hacemos filtros por categoría, referencia, etc.
        return listarTodos();
    }

    @Override
    public ProductoDto crear(CrearProductoRequest request) {
        validator.validarCrear(request);

        CategoriaEntity categoria = categoriaRepository.findById(request.getIdCategoria())
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));

        ProductoEntity entity = ProductoEntity.builder()
                .referencia(request.getReferencia())
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .categoria(categoria)
                // Stock inicial SIEMPRE 0
                .stock(0)
                .stockReservado(0)
                .fecha(OffsetDateTime.now())
                .build();

        ProductoEntity guardado = productoRepository.save(entity);
        return productoMapper.toDto(guardado);
    }

    @Override
    public ProductoDto obtenerPorId(Long id) {
        ProductoEntity entity = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        return productoMapper.toDto(entity);
    }

    @Override
    public ProductoDetalleDto obtenerDetalle(Long id) {
        ProductoEntity entity = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        // Aquí asumimos que tienes este método en el mapper.
        // Si no existe, el próximo error nos lo dirá y lo creamos.
        return productoMapper.toDetalleDto(entity);
    }

    @Override
    public ProductoDto actualizar(Long id, ActualizarProductoRequest request) {

        ProductoEntity entity = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        CategoriaEntity categoria = categoriaRepository.findById(request.getIdCategoria())
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));

        entity.setReferencia(request.getReferencia());
        entity.setNombre(request.getNombre());
        entity.setDescripcion(request.getDescripcion());
        entity.setCategoria(categoria);
        entity.setFecha(OffsetDateTime.now()); // última actualización

        ProductoEntity actualizado = productoRepository.save(entity);
        return productoMapper.toDto(actualizado);
    }

    @Override
    public void eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new IllegalArgumentException("El producto no existe");
        }
        // Más adelante: validar que no tenga seriales, movimientos, etc.
        productoRepository.deleteById(id);
    }
}