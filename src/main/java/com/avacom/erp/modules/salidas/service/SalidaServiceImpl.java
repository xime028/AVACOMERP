package com.avacom.erp.modules.salidas.service;

import com.avacom.erp.modules.salidas.dto.*;
import com.avacom.erp.modules.salidas.entity.*;
import com.avacom.erp.modules.salidas.mapper.RemisionSalidaMapper;
import com.avacom.erp.modules.salidas.mapper.SalidaItemMapper;
import com.avacom.erp.modules.salidas.mapper.SalidaMapper;
import com.avacom.erp.modules.salidas.repository.SalidaRepository;
import com.avacom.erp.modules.salidas.validator.SalidaBusinessValidator;
import com.avacom.erp.modules.clientes.entity.ClienteEntity;
import com.avacom.erp.modules.clientes.repository.ClienteRepository;
import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import com.avacom.erp.modules.usuarios.repository.UsuarioRepository;
import com.avacom.erp.modules.almacen.entity.ProductoEntity;
import com.avacom.erp.modules.almacen.entity.SerialEntity;
import com.avacom.erp.modules.almacen.repository.ProductoRepository;
import com.avacom.erp.modules.almacen.repository.SerialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class SalidaServiceImpl implements SalidaService {

    private final SalidaRepository salidaRepository;
    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final SerialRepository serialRepository;
    private final SalidaMapper salidaMapper;
    private final SalidaItemMapper salidaItemMapper;
    private final RemisionSalidaMapper remisionSalidaMapper;
    private final SalidaBusinessValidator validator;

    @Override
    public SalidaDto crear(CrearSalidaRequest request) {
        validator.validarCrear(request);

        ClienteEntity cliente = clienteRepository.findById(request.getIdCliente())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        UsuarioEntity usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        OffsetDateTime fechaSalida = request.getFechaSalida() != null
                ? request.getFechaSalida()
                : OffsetDateTime.now();

        String consecutivo = (request.getConsecutivo() != null && !request.getConsecutivo().isBlank())
                ? request.getConsecutivo()
                : generarConsecutivoAutomatico();

        SalidaEntity salida = new SalidaEntity();
        salida.setConsecutivo(consecutivo);
        salida.setRecibe(request.getRecibe());
        salida.setDepartamento(request.getDepartamento());
        salida.setCiudad(request.getCiudad());
        salida.setFechaSalida(fechaSalida);
        salida.setObservaciones(request.getObservaciones());
        salida.setCliente(cliente);
        salida.setUsuario(usuario);

        List<SalidaItemEntity> items = new ArrayList<>();

        for (CrearSalidaItemRequest itemReq : request.getItems()) {
            ProductoEntity producto = productoRepository.findById(itemReq.getIdProducto())
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado: " + itemReq.getIdProducto()));

            // Ajuste simple de stock (puedes refinarlo después)
            if (producto.getStock() != null && producto.getStock() < itemReq.getCantidad()) {
                throw new IllegalArgumentException("Stock insuficiente para producto: " + producto.getNombre());
            }
            producto.setStock(producto.getStock() - itemReq.getCantidad());
            productoRepository.save(producto);

            SalidaItemEntity item = new SalidaItemEntity();
            item.setSalida(salida);
            item.setProducto(producto);
            item.setCantidad(itemReq.getCantidad());

            List<SalidaSerialEntity> seriales = new ArrayList<>();
            if (itemReq.getIdsSeriales() != null) {
                for (Long idSerial : itemReq.getIdsSeriales()) {
                    SerialEntity serial = serialRepository.findById(idSerial)
                            .orElseThrow(() -> new IllegalArgumentException("Serial no encontrado: " + idSerial));

                    SalidaSerialEntity salidaSerial = new SalidaSerialEntity();
                    salidaSerial.setSalidaItem(item);
                    salidaSerial.setSerial(serial);
                    seriales.add(salidaSerial);
                }
            }
            item.setSeriales(seriales);
            items.add(item);
        }

        salida.setItems(items);

        SalidaEntity guardada = salidaRepository.save(salida);
        return salidaMapper.toDto(guardada);
    }

    @Override
    public SalidaDto actualizar(Long id, ActualizarSalidaRequest request) {
        SalidaEntity existente = salidaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Salida no encontrada"));

        validator.validarActualizar(existente, request);

        if (request.getRecibe() != null) {
            existente.setRecibe(request.getRecibe());
        }
        if (request.getDepartamento() != null) {
            existente.setDepartamento(request.getDepartamento());
        }
        if (request.getCiudad() != null) {
            existente.setCiudad(request.getCiudad());
        }
        if (request.getFechaSalida() != null) {
            existente.setFechaSalida(request.getFechaSalida());
        }
        if (request.getObservaciones() != null) {
            existente.setObservaciones(request.getObservaciones());
        }
        if (request.getIdCliente() != null) {
            ClienteEntity cliente = clienteRepository.findById(request.getIdCliente())
                    .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
            existente.setCliente(cliente);
        }
        if (request.getIdUsuario() != null) {
            UsuarioEntity usuario = usuarioRepository.findById(request.getIdUsuario())
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
            existente.setUsuario(usuario);
        }

        SalidaEntity actualizada = salidaRepository.save(existente);
        return salidaMapper.toDto(actualizada);
    }

    @Override
    @Transactional(readOnly = true)
    public SalidaDetalleDto obtenerDetalle(Long id) {
        SalidaEntity entity = salidaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Salida no encontrada"));

        return SalidaDetalleDto.builder()
                .id(entity.getId())
                .consecutivo(entity.getConsecutivo())
                .recibe(entity.getRecibe())
                .departamento(entity.getDepartamento())
                .ciudad(entity.getCiudad())
                .fechaSalida(entity.getFechaSalida())
                .observaciones(entity.getObservaciones())
                .idCliente(entity.getCliente() != null ? entity.getCliente().getId() : null)
                .nombreCliente(entity.getCliente() != null ? entity.getCliente().getNombre() : null)
                .idUsuario(entity.getUsuario() != null ? entity.getUsuario().getId() : null)
                .nombreUsuario(entity.getUsuario() != null ? entity.getUsuario().getNombre() : null)
                .items(entity.getItems() != null
                        ? entity.getItems().stream().map(salidaItemMapper::toDto).toList()
                        : List.of())
                .remisiones(entity.getRemisiones() != null
                        ? entity.getRemisiones().stream().map(remisionSalidaMapper::toDto).toList()
                        : List.of())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalidaDto> listarTodos() {
        return salidaRepository.findAll()
                .stream()
                .map(salidaMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalidaDto> buscarPorFiltro(SalidaFiltroRequest filtro) {
        return salidaRepository.findAll()
                .stream()
                .filter(s -> filtro.getConsecutivo() == null ||
                        Objects.equals(s.getConsecutivo(), filtro.getConsecutivo()))
                .filter(s -> filtro.getIdCliente() == null ||
                        (s.getCliente() != null && Objects.equals(s.getCliente().getId(), filtro.getIdCliente())))
                .filter(s -> filtro.getIdUsuario() == null ||
                        (s.getUsuario() != null && Objects.equals(s.getUsuario().getId(), filtro.getIdUsuario())))
                .filter(s -> filtro.getDepartamento() == null ||
                        Objects.equals(s.getDepartamento(), filtro.getDepartamento()))
                .filter(s -> filtro.getCiudad() == null ||
                        Objects.equals(s.getCiudad(), filtro.getCiudad()))
                .filter(s -> filtro.getFechaDesde() == null ||
                        (s.getFechaSalida() != null && !s.getFechaSalida().isBefore(filtro.getFechaDesde())))
                .filter(s -> filtro.getFechaHasta() == null ||
                        (s.getFechaSalida() != null && !s.getFechaSalida().isAfter(filtro.getFechaHasta())))
                .map(salidaMapper::toDto)
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        if (!salidaRepository.existsById(id)) {
            throw new IllegalArgumentException("Salida no encontrada");
        }
        salidaRepository.deleteById(id);
    }

    private String generarConsecutivoAutomatico() {
        // Simple: SAL-YYYYMMDDHHMMSS
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String sufijo = LocalDateTime.now().format(formatter);
        return "SAL-" + sufijo;
    }
}