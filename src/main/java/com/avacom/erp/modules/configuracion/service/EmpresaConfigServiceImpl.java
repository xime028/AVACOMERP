package com.avacom.erp.modules.configuracion.service;

import com.avacom.erp.modules.configuracion.dto.ActualizarEmpresaRequest;
import com.avacom.erp.modules.configuracion.dto.EmpresaDto;
import com.avacom.erp.modules.configuracion.entity.EmpresaEntity;
import com.avacom.erp.modules.configuracion.mapper.EmpresaMapper;
import com.avacom.erp.modules.configuracion.repository.EmpresaRepository;
import com.avacom.erp.modules.configuracion.validator.EmpresaBusinessValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class EmpresaConfigServiceImpl implements EmpresaConfigService {

    private final EmpresaRepository empresaRepository;
    private final EmpresaMapper empresaMapper;
    private final EmpresaBusinessValidator validator;

    @Override
    @Transactional(readOnly = true)
    public EmpresaDto obtener() {
        EmpresaEntity entity = empresaRepository.findTopByOrderByIdAsc()
                .orElseGet(this::crearEmpresaPorDefecto);

        return empresaMapper.toDto(entity);
    }

    @Override
    public EmpresaDto actualizar(ActualizarEmpresaRequest request) {
        EmpresaEntity existente = empresaRepository.findTopByOrderByIdAsc()
                .orElseGet(this::crearEmpresaPorDefecto);

        validator.validarActualizar(existente, request);

        existente.setNombre(request.getNombre());
        existente.setNit(request.getNit());
        existente.setDireccion(request.getDireccion());
        existente.setTelefono(request.getTelefono());
        existente.setCorreo(request.getCorreo());
        existente.setIdLogoArchivo(request.getIdLogoArchivo());

        EmpresaEntity guardada = empresaRepository.save(existente);
        return empresaMapper.toDto(guardada);
    }

    private EmpresaEntity crearEmpresaPorDefecto() {
        EmpresaEntity entity = EmpresaEntity.builder()
                .nombre("Empresa sin configurar")
                .nit("N/A")
                .direccion(null)
                .telefono(null)
                .correo(null)
                .idLogoArchivo(null)
                .fechaRegistro(OffsetDateTime.now())
                .build();
        return empresaRepository.save(entity);
    }
}