package com.avacom.erp.modules.entradas.service;

import com.avacom.erp.modules.entradas.dto.CrearRemisionEntradaRequest;
import com.avacom.erp.modules.entradas.dto.RemisionEntradaDto;
import com.avacom.erp.modules.entradas.entity.EntradaEntity;
import com.avacom.erp.modules.entradas.entity.RemisionEntradaEntity;
import com.avacom.erp.modules.entradas.mapper.RemisionEntradaMapper;
import com.avacom.erp.modules.entradas.repository.EntradaRepository;
import com.avacom.erp.modules.entradas.repository.RemisionEntradaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RemisionEntradaServiceImpl implements RemisionEntradaService {

    private final RemisionEntradaRepository remisionEntradaRepository;
    private final EntradaRepository entradaRepository;
    private final RemisionEntradaMapper remisionEntradaMapper;

    @Override
    public RemisionEntradaDto agregarRemision(Long idEntrada, CrearRemisionEntradaRequest request) {
        EntradaEntity entrada = entradaRepository.findById(idEntrada)
                .orElseThrow(() -> new IllegalArgumentException("Entrada no encontrada"));

        Boolean firmado = request.getFirmado() != null ? request.getFirmado() : Boolean.FALSE;

        RemisionEntradaEntity entity = RemisionEntradaEntity.builder()
                .entrada(entrada)
                .idDocumento(request.getIdDocumento())
                .firmado(firmado)
                .idFirmaDocumento(request.getIdFirmaDocumento())
                .hashFuncional(request.getHashFuncional())
                .fecha(OffsetDateTime.now())
                .build();

        RemisionEntradaEntity guardada = remisionEntradaRepository.save(entity);
        return remisionEntradaMapper.toDto(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RemisionEntradaDto> listarPorEntrada(Long idEntrada) {
        EntradaEntity entrada = entradaRepository.findById(idEntrada)
                .orElseThrow(() -> new IllegalArgumentException("Entrada no encontrada"));

        return remisionEntradaRepository.findByEntradaOrderByFechaAsc(entrada)
                .stream()
                .map(remisionEntradaMapper::toDto)
                .toList();
    }
}