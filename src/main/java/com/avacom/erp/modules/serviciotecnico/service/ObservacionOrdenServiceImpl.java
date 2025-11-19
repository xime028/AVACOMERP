package com.avacom.erp.modules.serviciotecnico.service;

import com.avacom.erp.modules.serviciotecnico.dto.CrearObservacionRequest;
import com.avacom.erp.modules.serviciotecnico.dto.ObservacionDto;
import com.avacom.erp.modules.serviciotecnico.entity.ObservacionOrdenEntity;
import com.avacom.erp.modules.serviciotecnico.entity.OrdenServicioEntity;
import com.avacom.erp.modules.serviciotecnico.mapper.ObservacionMapper;
import com.avacom.erp.modules.serviciotecnico.repository.ObservacionOrdenRepository;
import com.avacom.erp.modules.serviciotecnico.repository.OrdenServicioRepository;
import com.avacom.erp.modules.serviciotecnico.validator.ObservacionBusinessValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ObservacionOrdenServiceImpl implements ObservacionOrdenService {

    private final ObservacionOrdenRepository observacionRepository;
    private final OrdenServicioRepository ordenServicioRepository;
    private final ObservacionMapper observacionMapper;
    private final ObservacionBusinessValidator validator;

    @Override
    public ObservacionDto agregarObservacion(Long idOrden, CrearObservacionRequest request) {
        OrdenServicioEntity orden = ordenServicioRepository.findById(idOrden)
                .orElseThrow(() -> new IllegalArgumentException("Orden de servicio no encontrada"));

        validator.validarAgregar(orden);

        ObservacionOrdenEntity entity = ObservacionOrdenEntity.builder()
                .ordenServicio(orden)
                .observacion(request.getObservacion())
                .fecha(OffsetDateTime.now())
                .idArchivo(request.getIdArchivo())
                .build();

        ObservacionOrdenEntity guardada = observacionRepository.save(entity);
        return observacionMapper.toDto(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ObservacionDto> listarPorOrden(Long idOrden) {
        OrdenServicioEntity orden = ordenServicioRepository.findById(idOrden)
                .orElseThrow(() -> new IllegalArgumentException("Orden de servicio no encontrada"));

        return observacionRepository.findByOrdenServicioOrderByFechaAsc(orden)
                .stream()
                .map(observacionMapper::toDto)
                .toList();
    }
}