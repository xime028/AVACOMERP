package com.avacom.erp.modules.serviciotecnico.validator;

import com.avacom.erp.modules.serviciotecnico.dto.ActualizarOrdenServicioRequest;
import com.avacom.erp.modules.serviciotecnico.dto.CrearOrdenServicioRequest;
import com.avacom.erp.modules.serviciotecnico.entity.OrdenServicioEntity;
import com.avacom.erp.modules.clientes.repository.ClienteRepository;
import com.avacom.erp.modules.usuarios.repository.UsuarioRepository;
import com.avacom.erp.modules.almacen.repository.SerialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrdenServicioBusinessValidator {

    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final SerialRepository serialRepository;

    public void validarCrear(CrearOrdenServicioRequest request) {
        if (!clienteRepository.existsById(request.getIdCliente())) {
            throw new IllegalArgumentException("Cliente no existe");
        }
        if (request.getIdTecnico() != null &&
                !usuarioRepository.existsById(request.getIdTecnico())) {
            throw new IllegalArgumentException("Técnico no existe");
        }
        if (request.getIdSerial() != null &&
                !serialRepository.existsById(request.getIdSerial())) {
            throw new IllegalArgumentException("Serial no existe");
        }
    }

    public void validarActualizar(OrdenServicioEntity existente, ActualizarOrdenServicioRequest request) {
        if (request.getIdCliente() != null &&
                !clienteRepository.existsById(request.getIdCliente())) {
            throw new IllegalArgumentException("Cliente no existe");
        }
        if (request.getIdTecnico() != null &&
                !usuarioRepository.existsById(request.getIdTecnico())) {
            throw new IllegalArgumentException("Técnico no existe");
        }
        if (request.getIdSerial() != null &&
                !serialRepository.existsById(request.getIdSerial())) {
            throw new IllegalArgumentException("Serial no existe");
        }
    }
}