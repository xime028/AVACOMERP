package com.avacom.erp.modules.entradas.validator;

import com.avacom.erp.modules.entradas.dto.ActualizarEntradaRequest;
import com.avacom.erp.modules.entradas.dto.CrearEntradaItemRequest;
import com.avacom.erp.modules.entradas.dto.CrearEntradaRequest;
import com.avacom.erp.modules.entradas.entity.EntradaEntity;
import com.avacom.erp.modules.proveedores.repository.ProveedorRepository;
import com.avacom.erp.modules.usuarios.repository.UsuarioRepository;
import com.avacom.erp.modules.almacen.repository.ProductoRepository;
import com.avacom.erp.modules.almacen.repository.SerialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntradaBusinessValidator {

    private final ProveedorRepository proveedorRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final SerialRepository serialRepository;

    public void validarCrear(CrearEntradaRequest request) {
        if (!proveedorRepository.existsById(request.getIdProveedor())) {
            throw new IllegalArgumentException("Proveedor no existe");
        }
        if (!usuarioRepository.existsById(request.getIdUsuario())) {
            throw new IllegalArgumentException("Usuario no existe");
        }
        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new IllegalArgumentException("La entrada debe tener al menos un item");
        }

        for (CrearEntradaItemRequest item : request.getItems()) {
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

    public void validarActualizar(EntradaEntity existente, ActualizarEntradaRequest request) {
        if (request.getIdProveedor() != null &&
                !proveedorRepository.existsById(request.getIdProveedor())) {
            throw new IllegalArgumentException("Proveedor no existe");
        }
        if (request.getIdUsuario() != null &&
                !usuarioRepository.existsById(request.getIdUsuario())) {
            throw new IllegalArgumentException("Usuario no existe");
        }
    }
}