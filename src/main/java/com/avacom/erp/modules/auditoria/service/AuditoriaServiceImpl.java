package com.avacom.erp.modules.auditoria.service;

import com.avacom.erp.modules.auditoria.dto.*;
import com.avacom.erp.modules.auditoria.entity.AuditoriaDetalleEntity;
import com.avacom.erp.modules.auditoria.entity.AuditoriaEventoEntity;
import com.avacom.erp.modules.auditoria.mapper.AuditoriaDetalleMapper;
import com.avacom.erp.modules.auditoria.mapper.AuditoriaEventoMapper;
import com.avacom.erp.modules.auditoria.repository.AuditoriaDetalleRepository;
import com.avacom.erp.modules.auditoria.repository.AuditoriaEventoRepository;
import com.avacom.erp.modules.auditoria.validator.AuditoriaBusinessValidator;
import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import com.avacom.erp.modules.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class AuditoriaServiceImpl implements AuditoriaService {

    private final AuditoriaEventoRepository auditoriaEventoRepository;
    private final AuditoriaDetalleRepository auditoriaDetalleRepository;
    private final UsuarioRepository usuarioRepository;
    private final AuditoriaEventoMapper auditoriaEventoMapper;
    private final AuditoriaDetalleMapper auditoriaDetalleMapper;
    private final AuditoriaBusinessValidator validator;

    @Override
    public AuditoriaEventoDto registrar(RegistrarAuditoriaRequest request) {
        validator.validarRegistrar(request);

        UsuarioEntity usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        String accion = request.getAccion().toUpperCase();

        AuditoriaEventoEntity evento = new AuditoriaEventoEntity();
        evento.setUsuario(usuario);
        evento.setModulo(request.getModulo());
        evento.setEntidad(request.getEntidad());
        evento.setIdRegistro(request.getIdRegistro());
        evento.setAccion(accion);
        evento.setResumen(request.getResumen());
        evento.setIpOrigen(request.getIpOrigen());
        evento.setUserAgent(request.getUserAgent());
        evento.setExito(request.getExito());
        evento.setFecha(OffsetDateTime.now());

        List<AuditoriaDetalleEntity> detalles = new ArrayList<>();
        if (request.getDetalles() != null) {
            for (RegistrarAuditoriaDetalleRequest detReq : request.getDetalles()) {
                AuditoriaDetalleEntity det = new AuditoriaDetalleEntity();
                det.setEvento(evento);
                det.setCampo(detReq.getCampo());
                det.setValorAnterior(detReq.getValorAnterior());
                det.setValorNuevo(detReq.getValorNuevo());
                detalles.add(det);
            }
        }
        evento.setDetalles(detalles);

        AuditoriaEventoEntity guardado = auditoriaEventoRepository.save(evento);
        return auditoriaEventoMapper.toDto(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public AuditoriaEventoDetalleDto obtenerDetalle(Long id) {
        AuditoriaEventoEntity entity = auditoriaEventoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evento de auditoría no encontrado"));

        List<AuditoriaDetalleEntity> detalles = entity.getDetalles() != null
                ? entity.getDetalles()
                : auditoriaDetalleRepository.findByEvento(entity);

        return AuditoriaEventoDetalleDto.builder()
                .id(entity.getId())
                .idUsuario(entity.getUsuario() != null ? entity.getUsuario().getId() : null)
                .nombreUsuario(entity.getUsuario() != null ? entity.getUsuario().getNombre() : null)
                .correoUsuario(entity.getUsuario() != null ? entity.getUsuario().getCorreo() : null)
                .modulo(entity.getModulo())
                .entidad(entity.getEntidad())
                .idRegistro(entity.getIdRegistro())
                .accion(entity.getAccion())
                .resumen(entity.getResumen())
                .ipOrigen(entity.getIpOrigen())
                .userAgent(entity.getUserAgent())
                .exito(entity.getExito())
                .fecha(entity.getFecha())
                .detalles(detalles != null
                        ? detalles.stream().map(auditoriaDetalleMapper::toDto).toList()
                        : List.of())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuditoriaEventoDto> buscar(AuditoriaFiltroRequest filtro) {
        return auditoriaEventoRepository.findAll()
                .stream()
                .filter(e -> filtro.getIdUsuario() == null ||
                        (e.getUsuario() != null && Objects.equals(e.getUsuario().getId(), filtro.getIdUsuario())))
                .filter(e -> filtro.getModulo() == null ||
                        (e.getModulo() != null && e.getModulo().equalsIgnoreCase(filtro.getModulo())))
                .filter(e -> filtro.getEntidad() == null ||
                        (e.getEntidad() != null && e.getEntidad().equalsIgnoreCase(filtro.getEntidad())))
                .filter(e -> filtro.getIdRegistro() == null ||
                        Objects.equals(e.getIdRegistro(), filtro.getIdRegistro()))
                .filter(e -> filtro.getAccion() == null ||
                        (e.getAccion() != null && e.getAccion().equalsIgnoreCase(filtro.getAccion())))
                .filter(e -> filtro.getExito() == null ||
                        Objects.equals(e.getExito(), filtro.getExito()))
                .filter(e -> filtro.getFechaDesde() == null ||
                        (e.getFecha() != null && !e.getFecha().isBefore(filtro.getFechaDesde())))
                .filter(e -> filtro.getFechaHasta() == null ||
                        (e.getFecha() != null && !e.getFecha().isAfter(filtro.getFechaHasta())))
                .sorted(Comparator.comparing(AuditoriaEventoEntity::getFecha).reversed())
                .map(auditoriaEventoMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuditoriaEventoDto> listarUltimos(int limite) {
        if (limite <= 0) {
            limite = 50;
        }

        return auditoriaEventoRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(AuditoriaEventoEntity::getFecha).reversed())
                .limit(limite)
                .map(auditoriaEventoMapper::toDto)
                .toList();
    }
}