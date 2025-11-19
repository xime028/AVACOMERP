package com.avacom.erp.modules.almacen.validator;

import com.avacom.erp.modules.almacen.dto.ActualizarSerialRequest;
import com.avacom.erp.modules.almacen.dto.CrearSerialRequest;
import com.avacom.erp.modules.almacen.entity.SerialEntity;
import com.avacom.erp.modules.almacen.repository.ProductoRepository;
import com.avacom.erp.modules.almacen.repository.SerialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SerialBusinessValidator {

    private final SerialRepository serialRepository;
    private final ProductoRepository productoRepository;

    public void validarCrear(CrearSerialRequest request) {
        if (serialRepository.existsByNumeroSerial(request.getNumeroSerial())) {
            throw new IllegalArgumentException("Ya existe un serial con ese número");
        }
        if (!productoRepository.existsById(request.getIdProducto())) {
            throw new IllegalArgumentException("El producto no existe");
        }
    }

    public void validarActualizar(SerialEntity existente, ActualizarSerialRequest request) {
        if (request.getIdProducto() != null &&
                !productoRepository.existsById(request.getIdProducto())) {
            throw new IllegalArgumentException("El producto no existe");
        }
    }
}
