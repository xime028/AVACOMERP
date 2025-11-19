package com.avacom.erp.modules.serviciotecnico.service;

import com.avacom.erp.modules.serviciotecnico.dto.*;
import com.avacom.erp.modules.serviciotecnico.entity.OrdenServicioEntity;
import com.avacom.erp.modules.serviciotecnico.mapper.DocumentoOrdenMapper;
import com.avacom.erp.modules.serviciotecnico.mapper.NotificacionMapper;
import com.avacom.erp.modules.serviciotecnico.mapper.ObservacionMapper;
import com.avacom.erp.modules.serviciotecnico.mapper.OrdenServicioMapper;
import com.avacom.erp.modules.serviciotecnico.repository.OrdenServicioRepository;
import com.avacom.erp.modules.serviciotecnico.validator.OrdenServicioBusinessValidator;
import com.avacom.erp.modules.clientes.entity.ClienteEntity;
import com.avacom.erp.modules.clientes.repository.ClienteRepository;
import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import com.avacom.erp.modules.usuarios.repository.UsuarioRepository;
import com.avacom.erp.modules.almacen.entity.SerialEntity;
import com.avacom.erp.modules.almacen.repository.SerialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class OrdenServicioServiceImpl implements OrdenServicioService {

    private final OrdenServicioRepository ordenServicioRepository;
    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final SerialRepository serialRepository;
    private final OrdenServicioMapper ordenServicioMapper;
    private final ObservacionMapper observacionMapper;
    private final DocumentoOrdenMapper documentoOrdenMapper;
    private final NotificacionMapper notificacionMapper;
    private final OrdenServicioBusinessValidator validator;

    @Override
    public OrdenServicioDto crear(CrearOrdenServicioRequest request) {
        validator.validarCrear(request);

        ClienteEntity cliente = clienteRepository.findById(request.getIdCliente())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        UsuarioEntity tecnico = null;
        if (request.getIdTecnico() != null) {
            tecnico = usuarioRepository.findById(request.getIdTecnico())
                    .orElseThrow(() -> new IllegalArgumentException("Técnico no encontrado"));
        }

        SerialEntity serial = null;
        if (request.getIdSerial() != null) {
            serial = serialRepository.findById(request.getIdSerial())
                    .orElseThrow(() -> new IllegalArgumentException("Serial no encontrado"));
        }

        String estado = request.getEstado() != null ? request.getEstado() : "ABIERTA";
        OffsetDateTime ahora = OffsetDateTime.now();

        Integer consecutivo = generarConsecutivo();

        OrdenServicioEntity entity = OrdenServicioEntity.builder()
                .consecutivo(consecutivo)
                .descripcionDano(request.getDescripcionDano())
                .estado(estado)
                .observacionesGenerales(request.getObservacionesGenerales())
                .prioridad(request.getPrioridad())
                .fechaIngreso(ahora)
                .fechaActualizacion(ahora)
                .cliente(cliente)
                .tecnico(tecnico)
                .serial(serial)
                .idFalla(request.getIdFalla())
                .idSolucion(request.getIdSolucion())
                .idArchivo(request.getIdArchivo())
                .build();

        OrdenServicioEntity guardado = ordenServicioRepository.save(entity);
        return ordenServicioMapper.toDto(guardado);
    }

    @Override
    public OrdenServicioDto actualizar(Long id, ActualizarOrdenServicioRequest request) {
        OrdenServicioEntity existente = ordenServicioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Orden de servicio no encontrada"));

        validator.validarActualizar(existente, request);

        if (request.getDescripcionDano() != null) {
            existente.setDescripcionDano(request.getDescripcionDano());
        }
        if (request.getPrioridad() != null) {
            existente.setPrioridad(request.getPrioridad());
        }
        if (request.getEstado() != null) {
            existente.setEstado(request.getEstado());
        }
        if (request.getObservacionesGenerales() != null) {
            existente.setObservacionesGenerales(request.getObservacionesGenerales());
        }
        if (request.getIdCliente() != null) {
            ClienteEntity cliente = clienteRepository.findById(request.getIdCliente())
                    .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
            existente.setCliente(cliente);
        }
        if (request.getIdTecnico() != null) {
            UsuarioEntity tecnico = usuarioRepository.findById(request.getIdTecnico())
                    .orElseThrow(() -> new IllegalArgumentException("Técnico no encontrado"));
            existente.setTecnico(tecnico);
        }
        if (request.getIdSerial() != null) {
            SerialEntity serial = serialRepository.findById(request.getIdSerial())
                    .orElseThrow(() -> new IllegalArgumentException("Serial no encontrado"));
            existente.setSerial(serial);
        }
        if (request.getIdFalla() != null) {
            existente.setIdFalla(request.getIdFalla());
        }
        if (request.getIdSolucion() != null) {
            existente.setIdSolucion(request.getIdSolucion());
        }
        if (request.getIdArchivo() != null) {
            existente.setIdArchivo(request.getIdArchivo());
        }

        existente.setFechaActualizacion(OffsetDateTime.now());

        OrdenServicioEntity actualizado = ordenServicioRepository.save(existente);
        return ordenServicioMapper.toDto(actualizado);
    }

    @Override
    @Transactional(readOnly = true)
    public OrdenServicioDetalleDto obtenerDetalle(Long id) {
        OrdenServicioEntity entity = ordenServicioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Orden de servicio no encontrada"));

        // Construimos el detalle “a mano” usando los mappers de hijo
        return OrdenServicioDetalleDto.builder()
                .id(entity.getId())
                .consecutivo(entity.getConsecutivo())
                .descripcionDano(entity.getDescripcionDano())
                .estado(entity.getEstado())
                .prioridad(entity.getPrioridad())
                .observacionesGenerales(entity.getObservacionesGenerales())
                .fechaIngreso(entity.getFechaIngreso())
                .fechaActualizacion(entity.getFechaActualizacion())
                .idCliente(entity.getCliente() != null ? entity.getCliente().getId() : null)
                .nombreCliente(entity.getCliente() != null ? entity.getCliente().getNombre() : null)
                .idTecnico(entity.getTecnico() != null ? entity.getTecnico().getId() : null)
                .nombreTecnico(entity.getTecnico() != null ? entity.getTecnico().getNombre() : null)
                .idSerial(entity.getSerial() != null ? entity.getSerial().getId() : null)
                .numeroSerial(entity.getSerial() != null ? entity.getSerial().getNumeroSerial() : null)
                .idFalla(entity.getIdFalla())
                .idSolucion(entity.getIdSolucion())
                .idArchivo(entity.getIdArchivo())
                .observaciones(entity.getObservaciones() != null
                        ? entity.getObservaciones().stream().map(observacionMapper::toDto).toList()
                        : List.of())
                .documentos(entity.getDocumentos() != null
                        ? entity.getDocumentos().stream().map(documentoOrdenMapper::toDto).toList()
                        : List.of())
                .notificaciones(entity.getNotificaciones() != null
                        ? entity.getNotificaciones().stream().map(notificacionMapper::toDto).toList()
                        : List.of())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrdenServicioDto> listarTodos() {
        return ordenServicioRepository.findAll()
                .stream()
                .map(ordenServicioMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrdenServicioDto> buscarPorFiltro(OrdenServicioFiltroRequest filtro) {
        return ordenServicioRepository.findAll()
                .stream()
                .filter(o -> filtro.getConsecutivo() == null ||
                        Objects.equals(o.getConsecutivo(), filtro.getConsecutivo()))
                .filter(o -> filtro.getEstado() == null ||
                        Objects.equals(o.getEstado(), filtro.getEstado()))
                .filter(o -> filtro.getPrioridad() == null ||
                        Objects.equals(o.getPrioridad(), filtro.getPrioridad()))
                .filter(o -> filtro.getIdCliente() == null ||
                        (o.getCliente() != null && Objects.equals(o.getCliente().getId(), filtro.getIdCliente())))
                .filter(o -> filtro.getIdTecnico() == null ||
                        (o.getTecnico() != null && Objects.equals(o.getTecnico().getId(), filtro.getIdTecnico())))
                .filter(o -> filtro.getIdSerial() == null ||
                        (o.getSerial() != null && Objects.equals(o.getSerial().getId(), filtro.getIdSerial())))
                .filter(o -> filtro.getFechaDesde() == null ||
                        (o.getFechaIngreso() != null && !o.getFechaIngreso().isBefore(filtro.getFechaDesde())))
                .filter(o -> filtro.getFechaHasta() == null ||
                        (o.getFechaIngreso() != null && !o.getFechaIngreso().isAfter(filtro.getFechaHasta())))
                .map(ordenServicioMapper::toDto)
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        if (!ordenServicioRepository.existsById(id)) {
            throw new IllegalArgumentException("Orden de servicio no encontrada");
        }
        ordenServicioRepository.deleteById(id);
    }

    private Integer generarConsecutivo() {
        // implementación sencilla: max(consecutivo) + 1
        return ordenServicioRepository.findAll().stream()
                .map(OrdenServicioEntity::getConsecutivo)
                .filter(Objects::nonNull)
                .max(Integer::compareTo)
                .map(c -> c + 1)
                .orElse(1);
    }
}