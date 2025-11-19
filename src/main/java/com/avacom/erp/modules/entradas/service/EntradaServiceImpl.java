package com.avacom.erp.modules.entradas.service;

import com.avacom.erp.modules.entradas.dto.*;
import com.avacom.erp.modules.entradas.entity.*;
import com.avacom.erp.modules.entradas.mapper.EntradaItemMapper;
import com.avacom.erp.modules.entradas.mapper.EntradaMapper;
import com.avacom.erp.modules.entradas.mapper.RemisionEntradaMapper;
import com.avacom.erp.modules.entradas.repository.EntradaRepository;
import com.avacom.erp.modules.entradas.validator.EntradaBusinessValidator;
import com.avacom.erp.modules.proveedores.entity.ProveedorEntity;
import com.avacom.erp.modules.proveedores.repository.ProveedorRepository;
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
public class EntradaServiceImpl implements EntradaService {

    private final EntradaRepository entradaRepository;
    private final ProveedorRepository proveedorRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final SerialRepository serialRepository;
    private final EntradaMapper entradaMapper;
    private final EntradaItemMapper entradaItemMapper;
    private final RemisionEntradaMapper remisionEntradaMapper;
    private final EntradaBusinessValidator validator;

    @Override
    public EntradaDto crear(CrearEntradaRequest request) {
        validator.validarCrear(request);

        ProveedorEntity proveedor = proveedorRepository.findById(request.getIdProveedor())
                .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado"));

        UsuarioEntity usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        OffsetDateTime fecha = request.getFecha() != null ? request.getFecha() : OffsetDateTime.now();

        String consecutivo = (request.getConsecutivo() != null && !request.getConsecutivo().isBlank())
                ? request.getConsecutivo()
                : generarConsecutivoAutomatico();

        EntradaEntity entrada = new EntradaEntity();
        entrada.setConsecutivo(consecutivo);
        entrada.setObservaciones(request.getObservaciones());
        entrada.setFecha(fecha);
        entrada.setLote(request.getLote());
        entrada.setTipo(request.getTipo());
        entrada.setProveedor(proveedor);
        entrada.setUsuario(usuario);

        List<EntradaItemEntity> items = new ArrayList<>();

        for (CrearEntradaItemRequest itemReq : request.getItems()) {
            ProductoEntity producto = productoRepository.findById(itemReq.getIdProducto())
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado: " + itemReq.getIdProducto()));

            // Ajuste simple de stock: sumar
            Integer stockActual = producto.getStock() != null ? producto.getStock() : 0;
            producto.setStock(stockActual + itemReq.getCantidad());
            productoRepository.save(producto);

            EntradaItemEntity item = new EntradaItemEntity();
            item.setEntrada(entrada);
            item.setProducto(producto);
            item.setCantidad(itemReq.getCantidad());

            List<EntradaSerialEntity> seriales = new ArrayList<>();
            if (itemReq.getIdsSeriales() != null) {
                for (Long idSerial : itemReq.getIdsSeriales()) {
                    SerialEntity serial = serialRepository.findById(idSerial)
                            .orElseThrow(() -> new IllegalArgumentException("Serial no encontrado: " + idSerial));

                    EntradaSerialEntity entradaSerial = new EntradaSerialEntity();
                    entradaSerial.setEntradaItem(item);
                    entradaSerial.setSerial(serial);
                    seriales.add(entradaSerial);
                }
            }
            item.setSeriales(seriales);
            items.add(item);
        }

        entrada.setItems(items);

        EntradaEntity guardada = entradaRepository.save(entrada);
        return entradaMapper.toDto(guardada);
    }

    @Override
    public EntradaDto actualizar(Long id, ActualizarEntradaRequest request) {
        EntradaEntity existente = entradaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Entrada no encontrada"));

        validator.validarActualizar(existente, request);

        if (request.getObservaciones() != null) {
            existente.setObservaciones(request.getObservaciones());
        }
        if (request.getFecha() != null) {
            existente.setFecha(request.getFecha());
        }
        if (request.getLote() != null) {
            existente.setLote(request.getLote());
        }
        if (request.getTipo() != null) {
            existente.setTipo(request.getTipo());
        }
        if (request.getIdProveedor() != null) {
            ProveedorEntity proveedor = proveedorRepository.findById(request.getIdProveedor())
                    .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado"));
            existente.setProveedor(proveedor);
        }
        if (request.getIdUsuario() != null) {
            UsuarioEntity usuario = usuarioRepository.findById(request.getIdUsuario())
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
            existente.setUsuario(usuario);
        }

        EntradaEntity actualizada = entradaRepository.save(existente);
        return entradaMapper.toDto(actualizada);
    }

    @Override
    @Transactional(readOnly = true)
    public EntradaDetalleDto obtenerDetalle(Long id) {
        EntradaEntity entity = entradaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Entrada no encontrada"));

        return EntradaDetalleDto.builder()
                .id(entity.getId())
                .consecutivo(entity.getConsecutivo())
                .lote(entity.getLote())
                .tipo(entity.getTipo())
                .observaciones(entity.getObservaciones())
                .fecha(entity.getFecha())
                .idProveedor(entity.getProveedor() != null ? entity.getProveedor().getId() : null)
                .nombreProveedor(entity.getProveedor() != null ? entity.getProveedor().getNombre() : null)
                .idUsuario(entity.getUsuario() != null ? entity.getUsuario().getId() : null)
                .nombreUsuario(entity.getUsuario() != null ? entity.getUsuario().getNombre() : null)
                .items(entity.getItems() != null
                        ? entity.getItems().stream().map(entradaItemMapper::toDto).toList()
                        : List.of())
                .remisiones(entity.getRemisiones() != null
                        ? entity.getRemisiones().stream().map(remisionEntradaMapper::toDto).toList()
                        : List.of())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EntradaDto> listarTodos() {
        return entradaRepository.findAll()
                .stream()
                .map(entradaMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EntradaDto> buscarPorFiltro(EntradaFiltroRequest filtro) {
        return entradaRepository.findAll()
                .stream()
                .filter(e -> filtro.getConsecutivo() == null ||
                        Objects.equals(e.getConsecutivo(), filtro.getConsecutivo()))
                .filter(e -> filtro.getIdProveedor() == null ||
                        (e.getProveedor() != null && Objects.equals(e.getProveedor().getId(), filtro.getIdProveedor())))
                .filter(e -> filtro.getIdUsuario() == null ||
                        (e.getUsuario() != null && Objects.equals(e.getUsuario().getId(), filtro.getIdUsuario())))
                .filter(e -> filtro.getLote() == null ||
                        Objects.equals(e.getLote(), filtro.getLote()))
                .filter(e -> filtro.getTipo() == null ||
                        Objects.equals(e.getTipo(), filtro.getTipo()))
                .filter(e -> filtro.getFechaDesde() == null ||
                        (e.getFecha() != null && !e.getFecha().isBefore(filtro.getFechaDesde())))
                .filter(e -> filtro.getFechaHasta() == null ||
                        (e.getFecha() != null && !e.getFecha().isAfter(filtro.getFechaHasta())))
                .map(entradaMapper::toDto)
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        if (!entradaRepository.existsById(id)) {
            throw new IllegalArgumentException("Entrada no encontrada");
        }
        entradaRepository.deleteById(id);
    }

    private String generarConsecutivoAutomatico() {
        // ENT-YYYYMMDDHHMMSS
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String sufijo = LocalDateTime.now().format(formatter);
        return "ENT-" + sufijo;
    }
}