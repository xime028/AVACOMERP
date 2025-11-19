package com.avacom.erp.modules.clientes.validator;

import com.avacom.erp.modules.clientes.dto.ActualizarClienteRequest;
import com.avacom.erp.modules.clientes.dto.CrearClienteRequest;
import com.avacom.erp.modules.clientes.entity.ClienteEntity;
import com.avacom.erp.modules.clientes.repository.ClienteRepository;
import com.avacom.erp.modules.clientes.repository.GrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClienteBusinessValidator {

    private final ClienteRepository clienteRepository;
    private final GrupoRepository grupoRepository;

    public void validarCrear(CrearClienteRequest request) {
        if (clienteRepository.existsByIdentificacion(request.getIdentificacion())) {
            throw new IllegalArgumentException("Ya existe un cliente con esa identificación");
        }
        if (request.getCorreo() != null && clienteRepository.existsByCorreo(request.getCorreo())) {
            throw new IllegalArgumentException("Ya existe un cliente con ese correo");
        }
        if (request.getIdGrupo() != null &&
                !grupoRepository.existsById(request.getIdGrupo())) {
            throw new IllegalArgumentException("El grupo especificado no existe");
        }
    }

    public void validarActualizar(ClienteEntity existente, ActualizarClienteRequest request) {
        if (request.getCorreo() != null &&
                !request.getCorreo().equalsIgnoreCase(existente.getCorreo()) &&
                clienteRepository.existsByCorreo(request.getCorreo())) {
            throw new IllegalArgumentException("Ya existe un cliente con ese correo");
        }
        if (request.getIdGrupo() != null &&
                !grupoRepository.existsById(request.getIdGrupo())) {
            throw new IllegalArgumentException("El grupo especificado no existe");
        }
    }
}