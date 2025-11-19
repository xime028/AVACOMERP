package com.avacom.erp.modules.almacen.validator;

import com.avacom.erp.modules.almacen.dto.ActualizarProductoRequest;
import com.avacom.erp.modules.almacen.dto.CrearProductoRequest;
import com.avacom.erp.modules.almacen.entity.ProductoEntity;
import com.avacom.erp.modules.almacen.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductoBusinessValidator {

    private final ProductoRepository productoRepository;

    public void validarCrear(CrearProductoRequest request) {
        if (productoRepository.existsByReferencia(request.getReferencia())) {
            throw new IllegalArgumentException("Ya existe un producto con esa referencia");
        }

        if (request.getStock() != null && request.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }

        if (request.getStockReservado() != null && request.getStockReservado() < 0) {
            throw new IllegalArgumentException("El stock reservado no puede ser negativo");
        }
    }

    public void validarActualizar(ProductoEntity existente, ActualizarProductoRequest request) {
        if (request.getStock() != null && request.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }

        if (request.getStockReservado() != null && request.getStockReservado() < 0) {
            throw new IllegalArgumentException("El stock reservado no puede ser negativo");
        }
    }
}