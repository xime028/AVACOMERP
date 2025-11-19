package com.avacom.erp.modules.capacitaciones.service;

import com.avacom.erp.modules.capacitaciones.dto.*;
import com.avacom.erp.modules.capacitaciones.entity.CapacitacionEntity;
import com.avacom.erp.modules.capacitaciones.entity.TipoCapacitacionEntity;
import com.avacom.erp.modules.capacitaciones.mapper.CapacitacionMapper;
import com.avacom.erp.modules.capacitaciones.repository.CapacitacionRepository;
import com.avacom.erp.modules.capacitaciones.repository.TipoCapacitacionRepository;
import com.avacom.erp.modules.capacitaciones.validator.CapacitacionBusinessValidator;
import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import com.avacom.erp.modules.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class CapacitacionServiceImpl implements CapacitacionService {

    private final CapacitacionRepository capacitacionRepository;
    private final TipoCapacitacionRepository tipoCapacitacionRepository;
    private final UsuarioRepository usuarioRepository;
    private final CapacitacionMapper capacitacionMapper;
    private final CapacitacionBusinessValidator validator;

    @Override
    public CapacitacionDto crear(CrearCapacitacionRequest request) {
        validator.validarCrear(request);

        TipoCapacitacionEntity tipo = tipoCapacitacionRepository.findById(request.getIdTipoCapacitacion())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de capacitación no encontrado"));

        UsuarioEntity usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuario responsable no encontrado"));

        OffsetDateTime fecha = request.getFecha() != null ? request.getFecha() : OffsetDateTime.now();

        CapacitacionEntity entity = CapacitacionEntity.builder()
                .titulo(request.getTitulo())
                .fecha(fecha)
                .numeroHoras(request.getNumeroHoras())
                .numeroAsistentes(request.getNumeroAsistentes())
                .tipoCapacitacion(tipo)
                .idArchivo(request.getIdArchivo())
                .usuario(usuario)
                .build();

        CapacitacionEntity guardada = capacitacionRepository.save(entity);
        return capacitacionMapper.toDto(guardada);
    }

    @Override
    public CapacitacionDto actualizar(Long id, ActualizarCapacitacionRequest request) {
        CapacitacionEntity existente = capacitacionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Capacitación no encontrada"));

        validator.validarActualizar(existente, request);

        if (request.getTitulo() != null) {
            existente.setTitulo(request.getTitulo());
        }
        if (request.getFecha() != null) {
            existente.setFecha(request.getFecha());
        }
        if (request.getNumeroHoras() != null) {
            existente.setNumeroHoras(request.getNumeroHoras());
        }
        if (request.getNumeroAsistentes() != null) {
            existente.setNumeroAsistentes(request.getNumeroAsistentes());
        }
        if (request.getIdTipoCapacitacion() != null) {
            TipoCapacitacionEntity tipo = tipoCapacitacionRepository.findById(request.getIdTipoCapacitacion())
                    .orElseThrow(() -> new IllegalArgumentException("Tipo de capacitación no encontrado"));
            existente.setTipoCapacitacion(tipo);
        }
        if (request.getIdUsuario() != null) {
            UsuarioEntity usuario = usuarioRepository.findById(request.getIdUsuario())
                    .orElseThrow(() -> new IllegalArgumentException("Usuario responsable no encontrado"));
            existente.setUsuario(usuario);
        }
        if (request.getIdArchivo() != null) {
            existente.setIdArchivo(request.getIdArchivo());
        }

        CapacitacionEntity guardada = capacitacionRepository.save(existente);
        return capacitacionMapper.toDto(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public CapacitacionDetalleDto obtenerDetalle(Long id) {
        CapacitacionEntity entity = capacitacionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Capacitación no encontrada"));

        return CapacitacionDetalleDto.builder()
                .id(entity.getId())
                .titulo(entity.getTitulo())
                .fecha(entity.getFecha())
                .numeroHoras(entity.getNumeroHoras())
                .numeroAsistentes(entity.getNumeroAsistentes())
                .idTipoCapacitacion(entity.getTipoCapacitacion() != null ? entity.getTipoCapacitacion().getId() : null)
                .codigoTipo(entity.getTipoCapacitacion() != null ? entity.getTipoCapacitacion().getCodigoTipo() : null)
                .tema(entity.getTipoCapacitacion() != null ? entity.getTipoCapacitacion().getTema() : null)
                .idUsuario(entity.getUsuario() != null ? entity.getUsuario().getId() : null)
                .nombreUsuario(entity.getUsuario() != null ? entity.getUsuario().getNombre() : null)
                .correoUsuario(entity.getUsuario() != null ? entity.getUsuario().getCorreo() : null)
                .idArchivo(entity.getIdArchivo())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CapacitacionDto> listar() {
        return capacitacionRepository.findAll()
                .stream()
                .map(capacitacionMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CapacitacionDto> buscarPorFiltro(CapacitacionFiltroRequest filtro) {
        return capacitacionRepository.findAll()
                .stream()
                .filter(c -> filtro.getIdTipoCapacitacion() == null ||
                        (c.getTipoCapacitacion() != null &&
                                Objects.equals(c.getTipoCapacitacion().getId(), filtro.getIdTipoCapacitacion())))
                .filter(c -> filtro.getIdUsuario() == null ||
                        (c.getUsuario() != null &&
                                Objects.equals(c.getUsuario().getId(), filtro.getIdUsuario())))
                .filter(c -> filtro.getFechaDesde() == null ||
                        (c.getFecha() != null && !c.getFecha().isBefore(filtro.getFechaDesde())))
                .filter(c -> filtro.getFechaHasta() == null ||
                        (c.getFecha() != null && !c.getFecha().isAfter(filtro.getFechaHasta())))
                .filter(c -> filtro.getTituloContiene() == null ||
                        (c.getTitulo() != null &&
                                c.getTitulo().toLowerCase().contains(filtro.getTituloContiene().toLowerCase())))
                .map(capacitacionMapper::toDto)
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        if (!capacitacionRepository.existsById(id)) {
            throw new IllegalArgumentException("Capacitación no encontrada");
        }
        capacitacionRepository.deleteById(id);
    }
}