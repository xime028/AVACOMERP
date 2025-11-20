package com.avacom.erp.modules.almacen.validator;

import com.avacom.erp.modules.almacen.dto.CrearProductoRequest;
import com.avacom.erp.modules.almacen.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductoBusinessValidator {

    private final ProductoRepository productoRepository;

    public void validarCrear(CrearProductoRequest request) {
        // Solo reglas de negocio, nada de stock aquí

        // Ejemplo: referencia única
        if (productoRepository.existsByReferenciaIgnoreCase(request.getReferencia())) {
            throw new IllegalArgumentException("Ya existe un producto con la misma referencia");
        }
    }
}