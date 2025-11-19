package com.avacom.erp.modules.serviciotecnico.service;

import com.avacom.erp.modules.serviciotecnico.dto.CrearNotificacionRequest;
import com.avacom.erp.modules.serviciotecnico.dto.NotificacionDto;
import com.avacom.erp.modules.serviciotecnico.entity.NotificacionEntity;
import com.avacom.erp.modules.serviciotecnico.entity.OrdenServicioEntity;
import com.avacom.erp.modules.serviciotecnico.mapper.NotificacionMapper;
import com.avacom.erp.modules.serviciotecnico.repository.NotificacionRepository;
import com.avacom.erp.modules.serviciotecnico.repository.OrdenServicioRepository;
import com.avacom.erp.modules.serviciotecnico.validator.NotificacionBusinessValidator;
import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import com.avacom.erp.modules.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificacionServiceImpl implements NotificacionService {

    private final NotificacionRepository notificacionRepository;
    private final OrdenServicioRepository ordenServicioRepository;
    private final UsuarioRepository usuarioRepository;
    private final NotificacionMapper notificacionMapper;
    private final NotificacionBusinessValidator validator;

    @Override
    public NotificacionDto crearParaOrden(Long idOrden, CrearNotificacionRequest request) {
        OrdenServicioEntity orden = ordenServicioRepository.findById(idOrden)
                .orElseThrow(() -> new IllegalArgumentException("Orden de servicio no encontrada"));

        validator.validarCrear(request.getIdUsuario());

        UsuarioEntity usuario = null;
        if (request.getIdUsuario() != null) {
            usuario = usuarioRepository.findById(request.getIdUsuario())
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        }

        NotificacionEntity entity = NotificacionEntity.builder()
                .ordenServicio(orden)
                .usuario(usuario)
                .mensaje(request.getMensaje())
                .fecha(OffsetDateTime.now())
                .build();

        NotificacionEntity guardada = notificacionRepository.save(entity);
        return notificacionMapper.toDto(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificacionDto> listarPorOrden(Long idOrden) {
        OrdenServicioEntity orden = ordenServicioRepository.findById(idOrden)
                .orElseThrow(() -> new IllegalArgumentException("Orden de servicio no encontrada"));

        return notificacionRepository.findByOrdenServicioOrderByFechaDesc(orden)
                .stream()
                .map(notificacionMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificacionDto> listarPorUsuario(Long idUsuario) {
        UsuarioEntity usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        return notificacionRepository.findByUsuarioOrderByFechaDesc(usuario)
                .stream()
                .map(notificacionMapper::toDto)
                .toList();
    }
}