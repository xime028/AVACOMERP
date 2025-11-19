package com.avacom.erp.modules.salidas.validator;

import com.avacom.erp.modules.salidas.dto.CrearSalidaItemRequest;
import com.avacom.erp.modules.salidas.dto.CrearSalidaRequest;
import com.avacom.erp.modules.salidas.dto.ActualizarSalidaRequest;
import com.avacom.erp.modules.salidas.entity.SalidaEntity;
import com.avacom.erp.modules.clientes.repository.ClienteRepository;
import com.avacom.erp.modules.usuarios.repository.UsuarioRepository;
import com.avacom.erp.modules.almacen.repository.ProductoRepository;
import com.avacom.erp.modules.almacen.repository.SerialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SalidaBusinessValidator {

    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final SerialRepository serialRepository;

    public void validarCrear(CrearSalidaRequest request) {
        if (!clienteRepository.existsById(request.getIdCliente())) {
            throw new IllegalArgumentException("Cliente no existe");
        }
        if (!usuarioRepository.existsById(request.getIdUsuario())) {
            throw new IllegalArgumentException("Usuario no existe");
        }
        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new IllegalArgumentException("La salida debe tener al menos un item");
        }

        for (CrearSalidaItemRequest item : request.getItems()) {
            if (!productoRepository.existsById(item.getIdProducto())) {
                throw new IllegalArgumentException("Producto no existe: " + item.getIdProducto());
            }
            if (item.getCantidad() == null || item.getCantidad() <= 0) {
                throw new IllegalArgumentException("Cantidad inválida para producto: " + item.getIdProducto());
            }

            if (item.getIdsSeriales() != null) {
                for (Long idSerial : item.getIdsSeriales()) {
                    if (!serialRepository.existsById(idSerial)) {
                        throw new IllegalArgumentException("Serial no existe: " + idSerial);
                    }
                }
            }
        }
    }

    public void validarActualizar(SalidaEntity existente, ActualizarSalidaRequest request) {
        if (request.getIdCliente() != null &&
                !clienteRepository.existsById(request.getIdCliente())) {
            throw new IllegalArgumentException("Cliente no existe");
        }
        if (request.getIdUsuario() != null &&
                !usuarioRepository.existsById(request.getIdUsuario())) {
            throw new IllegalArgumentException("Usuario no existe");
        }
    }
}