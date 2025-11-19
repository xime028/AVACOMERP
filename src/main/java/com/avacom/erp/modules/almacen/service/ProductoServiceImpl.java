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
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoMapper productoMapper;
    private final ProductoBusinessValidator productoValidator;

    @Override
    public ProductoDto crear(CrearProductoRequest request) {
        productoValidator.validarCrear(request);

        CategoriaEntity categoria = categoriaRepository.findById(request.getIdCategoria())
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));

        Integer stockReservado = request.getStockReservado() != null ? request.getStockReservado() : 0;

        ProductoEntity entity = ProductoEntity.builder()
                .referencia(request.getReferencia())
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .categoria(categoria)
                .stock(request.getStock())
                .stockReservado(stockReservado)
                .fecha(OffsetDateTime.now())
                .build();

        ProductoEntity guardado = productoRepository.save(entity);
        return productoMapper.toDto(guardado);
    }

    @Override
    public ProductoDto actualizar(Long id, ActualizarProductoRequest request) {
        ProductoEntity existente = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        productoValidator.validarActualizar(existente, request);

        if (request.getNombre() != null) {
            existente.setNombre(request.getNombre());
        }
        if (request.getDescripcion() != null) {
            existente.setDescripcion(request.getDescripcion());
        }
        if (request.getStock() != null) {
            existente.setStock(request.getStock());
        }
        if (request.getStockReservado() != null) {
            existente.setStockReservado(request.getStockReservado());
        }
        if (request.getIdCategoria() != null) {
            CategoriaEntity categoria = categoriaRepository.findById(request.getIdCategoria())
                    .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
            existente.setCategoria(categoria);
        }

        ProductoEntity actualizado = productoRepository.save(existente);
        return productoMapper.toDto(actualizado);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoDetalleDto obtenerDetalle(Long id) {
        ProductoEntity entity = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        return productoMapper.toDetalleDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDto> listarTodos() {
        return productoRepository.findAll()
                .stream()
                .map(productoMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDto> buscarPorFiltro(ProductoFiltroRequest filtro) {
        return productoRepository.findAll()
                .stream()
                .filter(p -> filtro.getNombre() == null ||
                        (p.getNombre() != null && p.getNombre().toLowerCase().contains(filtro.getNombre().toLowerCase())))
                .filter(p -> filtro.getReferencia() == null ||
                        Objects.equals(p.getReferencia(), filtro.getReferencia()))
                .filter(p -> filtro.getIdCategoria() == null ||
                        (p.getCategoria() != null && Objects.equals(p.getCategoria().getId(), filtro.getIdCategoria())))
                .map(productoMapper::toDto)
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        ProductoEntity entity = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        // Aquí podrías aplicar lógica de “no borrar si tiene seriales o movimientos”
        productoRepository.delete(entity);
    }
}