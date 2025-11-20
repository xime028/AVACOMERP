package com.avacom.erp.modules.almacen.validator;

import com.avacom.erp.modules.almacen.dto.ActualizarSerialRequest;
import com.avacom.erp.modules.almacen.dto.CrearSerialRequest;
import com.avacom.erp.modules.almacen.entity.SerialEntity;
import com.avacom.erp.modules.almacen.repository.ProductoRepository;
import com.avacom.erp.modules.almacen.repository.SerialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class SerialBusinessValidator {

    private final SerialRepository serialRepository;
    private final ProductoRepository productoRepository;

    private static final Set<String> ESTADOS_VALIDOS = Set.of(
            "DISPONIBLE", "RESERVADO", "ENTREGADO", "EN_SERVICIO"
    );

    public void validarCrear(CrearSerialRequest request) {
        if (request.getIdProducto() == null) {
            throw new IllegalArgumentException("El producto es obligatorio");
        }
        if (!productoRepository.existsById(request.getIdProducto())) {
            throw new IllegalArgumentException("El producto no existe");
        }
        if (serialRepository.existsByNumeroSerialIgnoreCase(request.getNumeroSerial())) {
            throw new IllegalArgumentException("Ya existe un serial con ese número");
        }

        String estado = request.getEstado();
        if (estado == null || estado.isBlank()) {
            estado = "DISPONIBLE";
            request.setEstado(estado);
        }
        validarEstado(estado);
    }

    public void validarActualizar(SerialEntity existente, ActualizarSerialRequest request) {
        if (request.getEstado() != null) {
            validarEstado(request.getEstado());
        }
        if (request.getIdProducto() != null &&
                !productoRepository.existsById(request.getIdProducto())) {
            throw new IllegalArgumentException("El producto no existe");
        }
    }

    public void validarCambioEstado(String nuevoEstado) {
        validarEstado(nuevoEstado);
    }

    private void validarEstado(String estado) {
        if (estado == null || estado.isBlank()) {
            throw new IllegalArgumentException("El estado del serial es obligatorio");
        }
        String upper = estado.toUpperCase();
        if (!ESTADOS_VALIDOS.contains(upper)) {
            throw new IllegalArgumentException("Estado de serial inválido. " +
                    "Permitidos: DISPONIBLE, RESERVADO, ENTREGADO, EN_SERVICIO");
        }
    }
}