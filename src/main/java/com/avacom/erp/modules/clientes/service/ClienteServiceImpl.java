package com.avacom.erp.modules.clientes.service;

import com.avacom.erp.modules.archivos.entity.ArchivoEntity;
import com.avacom.erp.modules.archivos.repository.ArchivoRepository;
import com.avacom.erp.modules.clientes.dto.*;
import com.avacom.erp.modules.clientes.entity.ClienteEntity;
import com.avacom.erp.modules.clientes.entity.GrupoEntity;
import com.avacom.erp.modules.clientes.mapper.ClienteMapper;
import com.avacom.erp.modules.clientes.repository.ClienteRepository;
import com.avacom.erp.modules.clientes.repository.GrupoRepository;
import com.avacom.erp.modules.clientes.validator.ClienteBusinessValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final GrupoRepository grupoRepository;
    private final ClienteMapper clienteMapper;
    private final ClienteBusinessValidator clienteValidator;
    private final ArchivoRepository archivoRepository;

    @Override
    public ClienteDto crear(CrearClienteRequest request) {
        clienteValidator.validarCrear(request);

        GrupoEntity grupo = null;
        if (request.getIdGrupo() != null) {
            grupo = grupoRepository.findById(request.getIdGrupo())
                    .orElseThrow(() -> new IllegalArgumentException("El grupo no existe"));
        }

        ArchivoEntity archivo = null;
        if (request.getIdArchivo() != null) {
            archivo = archivoRepository.findById(request.getIdArchivo())
                    .orElseThrow(() -> new IllegalArgumentException("El archivo no existe"));
        }

        ClienteEntity entity = ClienteEntity.builder()
                .identificacion(request.getIdentificacion())
                .nombre(request.getNombre())
                .correo(request.getCorreo())
                .telefono(request.getTelefono())
                .direccion(request.getDireccion())
                .estado(request.getEstado())
                .tipoCliente(request.getTipoCliente())
                .fechaRegistro(OffsetDateTime.now())
                .grupo(grupo)
                .archivo(archivo)              // 👈 en lugar de .idArchivo(...)
                .build();

        ClienteEntity guardado = clienteRepository.save(entity);
        return clienteMapper.toDto(guardado);
    }

    @Override
    public ClienteDto actualizar(Long id, ActualizarClienteRequest request) {
        ClienteEntity existente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        clienteValidator.validarActualizar(existente, request);

        if (request.getNombre() != null) {
            existente.setNombre(request.getNombre());
        }
        if (request.getCorreo() != null) {
            existente.setCorreo(request.getCorreo());
        }
        if (request.getTelefono() != null) {
            existente.setTelefono(request.getTelefono());
        }
        if (request.getDireccion() != null) {
            existente.setDireccion(request.getDireccion());
        }
        if (request.getTipoCliente() != null) {
            existente.setTipoCliente(request.getTipoCliente());
        }
        if (request.getEstado() != null) {
            existente.setEstado(request.getEstado());
        }
        if (request.getIdArchivo() != null) {
            existente.setId(request.getIdArchivo());
        }
        if (request.getIdGrupo() != null) {
            GrupoEntity grupo = grupoRepository.findById(request.getIdGrupo())
                    .orElseThrow(() -> new IllegalArgumentException("Grupo no encontrado"));
            existente.setGrupo(grupo);
        }

        ClienteEntity actualizado = clienteRepository.save(existente);
        return clienteMapper.toDto(actualizado);
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteDetalleDto obtenerDetalle(Long id) {
        ClienteEntity entity = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        return clienteMapper.toDetalleDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteDto> listarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteDto> buscarPorFiltro(ClienteFiltroRequest filtro) {
        // implementación simple: filtramos en memoria a partir de findAll()
        return clienteRepository.findAll()
                .stream()
                .filter(c -> filtro.getNombre() == null ||
                        c.getNombre() != null &&
                                c.getNombre().toLowerCase().contains(filtro.getNombre().toLowerCase()))
                .filter(c -> filtro.getIdentificacion() == null ||
                        Objects.equals(c.getIdentificacion(), filtro.getIdentificacion()))
                .filter(c -> filtro.getTipoCliente() == null ||
                        Objects.equals(c.getTipoCliente(), filtro.getTipoCliente()))
                .filter(c -> filtro.getIdGrupo() == null ||
                        (c.getGrupo() != null && Objects.equals(c.getGrupo().getId(), filtro.getIdGrupo())))
                .filter(c -> filtro.getEstado() == null ||
                        Objects.equals(c.getEstado(), filtro.getEstado()))
                .filter(c -> filtro.getFechaDesde() == null ||
                        (c.getFechaRegistro() != null && !c.getFechaRegistro().isBefore(filtro.getFechaDesde())))
                .filter(c -> filtro.getFechaHasta() == null ||
                        (c.getFechaRegistro() != null && !c.getFechaRegistro().isAfter(filtro.getFechaHasta())))
                .map(clienteMapper::toDto)
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        ClienteEntity entity = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        // regla de negocio: borrado lógico
        entity.setEstado(false);
        clienteRepository.save(entity);
    }
}