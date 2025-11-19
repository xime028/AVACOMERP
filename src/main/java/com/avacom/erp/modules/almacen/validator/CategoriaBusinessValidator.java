package com.avacom.erp.modules.almacen.validator;

import com.avacom.erp.modules.almacen.dto.ActualizarCategoriaRequest;
import com.avacom.erp.modules.almacen.dto.CrearCategoriaRequest;
import com.avacom.erp.modules.almacen.entity.CategoriaEntity;
import com.avacom.erp.modules.almacen.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoriaBusinessValidator {

    private final CategoriaRepository categoriaRepository;

    public void validarCrear(CrearCategoriaRequest request) {
        if (categoriaRepository.existsByNombreIgnoreCase(request.getNombre())) {
            throw new IllegalArgumentException("Ya existe una categoría con ese nombre");
        }
    }

    public void validarActualizar(CategoriaEntity existente, ActualizarCategoriaRequest request) {
        if (request.getNombre() != null &&
                !request.getNombre().equalsIgnoreCase(existente.getNombre()) &&
                categoriaRepository.existsByNombreIgnoreCase(request.getNombre())) {
            throw new IllegalArgumentException("Ya existe una categoría con ese nombre");
        }
    }
}