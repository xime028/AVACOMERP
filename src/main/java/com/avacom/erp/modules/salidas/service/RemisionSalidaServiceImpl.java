package com.avacom.erp.modules.salidas.service;

import com.avacom.erp.modules.salidas.dto.CrearRemisionSalidaRequest;
import com.avacom.erp.modules.salidas.dto.RemisionSalidaDto;
import com.avacom.erp.modules.salidas.entity.RemisionSalidaEntity;
import com.avacom.erp.modules.salidas.entity.SalidaEntity;
import com.avacom.erp.modules.salidas.mapper.RemisionSalidaMapper;
import com.avacom.erp.modules.salidas.repository.RemisionSalidaRepository;
import com.avacom.erp.modules.salidas.repository.SalidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RemisionSalidaServiceImpl implements RemisionSalidaService {

    private final RemisionSalidaRepository remisionSalidaRepository;
    private final SalidaRepository salidaRepository;
    private final RemisionSalidaMapper remisionSalidaMapper;

    @Override
    public RemisionSalidaDto agregarRemision(Long idSalida, CrearRemisionSalidaRequest request) {
        SalidaEntity salida = salidaRepository.findById(idSalida)
                .orElseThrow(() -> new IllegalArgumentException("Salida no encontrada"));

        Boolean firmado = request.getFirmado() != null ? request.getFirmado() : Boolean.FALSE;

        RemisionSalidaEntity entity = RemisionSalidaEntity.builder()
                .salida(salida)
                .idDocumento(request.getIdDocumento())
                .firmado(firmado)
                .idFirmaDocumento(request.getIdFirmaDocumento())
                .hashFuncional(request.getHashFuncional())
                .fecha(OffsetDateTime.now())
                .build();

        RemisionSalidaEntity guardada = remisionSalidaRepository.save(entity);
        return remisionSalidaMapper.toDto(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RemisionSalidaDto> listarPorSalida(Long idSalida) {
        SalidaEntity salida = salidaRepository.findById(idSalida)
                .orElseThrow(() -> new IllegalArgumentException("Salida no encontrada"));

        return remisionSalidaRepository.findBySalidaOrderByFechaAsc(salida)
                .stream()
                .map(remisionSalidaMapper::toDto)
                .toList();
    }
}