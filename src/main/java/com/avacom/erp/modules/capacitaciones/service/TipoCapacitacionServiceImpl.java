package com.avacom.erp.modules.capacitaciones.service;

import com.avacom.erp.modules.capacitaciones.dto.*;
import com.avacom.erp.modules.capacitaciones.entity.TipoCapacitacionEntity;
import com.avacom.erp.modules.capacitaciones.mapper.TipoCapacitacionMapper;
import com.avacom.erp.modules.capacitaciones.repository.TipoCapacitacionRepository;
import com.avacom.erp.modules.capacitaciones.validator.TipoCapacitacionBusinessValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TipoCapacitacionServiceImpl implements TipoCapacitacionService {

    private final TipoCapacitacionRepository tipoCapacitacionRepository;
    private final TipoCapacitacionMapper tipoCapacitacionMapper;
    private final TipoCapacitacionBusinessValidator validator;

    @Override
    public TipoCapacitacionDto crear(CrearTipoCapacitacionRequest request) {
        validator.validarCrear(request);

        TipoCapacitacionEntity entity = TipoCapacitacionEntity.builder()
                .codigoTipo(request.getCodigoTipo())
                .tema(request.getTema())
                .fecha(OffsetDateTime.now())
                .build();

        TipoCapacitacionEntity guardada = tipoCapacitacionRepository.save(entity);
        return tipoCapacitacionMapper.toDto(guardada);
    }

    @Override
    public TipoCapacitacionDto actualizar(Long id, ActualizarTipoCapacitacionRequest request) {
        TipoCapacitacionEntity existente = tipoCapacitacionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de capacitación no encontrado"));

        validator.validarActualizar(existente, request);

        if (request.getCodigoTipo() != null) {
            existente.setCodigoTipo(request.getCodigoTipo());
        }
        if (request.getTema() != null) {
            existente.setTema(request.getTema());
        }

        TipoCapacitacionEntity guardada = tipoCapacitacionRepository.save(existente);
        return tipoCapacitacionMapper.toDto(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public TipoCapacitacionDto obtener(Long id) {
        TipoCapacitacionEntity entity = tipoCapacitacionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de capacitación no encontrado"));
        return tipoCapacitacionMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoCapacitacionDto> listar() {
        return tipoCapacitacionRepository.findAll()
                .stream()
                .map(tipoCapacitacionMapper::toDto)
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        if (!tipoCapacitacionRepository.existsById(id)) {
            throw new IllegalArgumentException("Tipo de capacitación no encontrado");
        }
        tipoCapacitacionRepository.deleteById(id);
    }
}