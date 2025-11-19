package com.avacom.erp.modules.almacen.service;

import com.avacom.erp.modules.almacen.dto.MovimientoSerialDto;
import com.avacom.erp.modules.almacen.dto.MovimientoSerialFiltroRequest;
import com.avacom.erp.modules.almacen.entity.MovimientoSerialEntity;
import com.avacom.erp.modules.almacen.entity.SerialEntity;
import com.avacom.erp.modules.almacen.mapper.MovimientoSerialMapper;
import com.avacom.erp.modules.almacen.repository.MovimientoSerialRepository;
import com.avacom.erp.modules.almacen.repository.SerialRepository;
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
public class MovimientoSerialServiceImpl implements MovimientoSerialService {

    private final MovimientoSerialRepository movimientoSerialRepository;
    private final SerialRepository serialRepository;
    private final UsuarioRepository usuarioRepository;
    private final MovimientoSerialMapper movimientoSerialMapper;

    @Override
    public MovimientoSerialDto registrarMovimiento(SerialEntity serial,
                                                   String tipo,
                                                   String modulo,
                                                   Long idReferencia,
                                                   UsuarioEntity usuario) {

        if (serial.getId() == null) {
            serial = serialRepository.findById(serial.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Serial no encontrado"));
        }
        if (usuario.getId() == null) {
            usuario = usuarioRepository.findById(usuario.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        }

        MovimientoSerialEntity entity = MovimientoSerialEntity.builder()
                .serial(serial)
                .tipo(tipo)
                .modulo(modulo)
                .idReferencia(idReferencia)
                .fecha(OffsetDateTime.now())
                .usuario(usuario)
                .build();

        MovimientoSerialEntity guardado = movimientoSerialRepository.save(entity);
        return movimientoSerialMapper.toDto(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovimientoSerialDto> buscar(MovimientoSerialFiltroRequest filtro) {
        return movimientoSerialRepository.findAll()
                .stream()
                .filter(m -> filtro.getIdSerial() == null ||
                        (m.getSerial() != null &&
                                Objects.equals(m.getSerial().getId(), filtro.getIdSerial())))
                .filter(m -> filtro.getNumeroSerial() == null ||
                        (m.getSerial() != null && m.getSerial().getNumeroSerial() != null &&
                                m.getSerial().getNumeroSerial().equalsIgnoreCase(filtro.getNumeroSerial())))
                .filter(m -> filtro.getTipo() == null ||
                        (m.getTipo() != null && m.getTipo().equalsIgnoreCase(filtro.getTipo())))
                .filter(m -> filtro.getModulo() == null ||
                        (m.getModulo() != null && m.getModulo().equalsIgnoreCase(filtro.getModulo())))
                .filter(m -> filtro.getIdUsuario() == null ||
                        (m.getUsuario() != null &&
                                Objects.equals(m.getUsuario().getId(), filtro.getIdUsuario())))
                .filter(m -> filtro.getFechaDesde() == null ||
                        (m.getFecha() != null && !m.getFecha().isBefore(filtro.getFechaDesde())))
                .filter(m -> filtro.getFechaHasta() == null ||
                        (m.getFecha() != null && !m.getFecha().isAfter(filtro.getFechaHasta())))
                .map(movimientoSerialMapper::toDto)
                .toList();
    }
}