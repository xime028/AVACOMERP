package com.avacom.erp.modules.almacen.service;

import com.avacom.erp.modules.almacen.dto.ActualizarSerialRequest;
import com.avacom.erp.modules.almacen.dto.CrearSerialRequest;
import com.avacom.erp.modules.almacen.dto.SerialDto;
import com.avacom.erp.modules.almacen.dto.SerialFiltroRequest;
import com.avacom.erp.modules.almacen.entity.ProductoEntity;
import com.avacom.erp.modules.almacen.entity.SerialEntity;
import com.avacom.erp.modules.almacen.mapper.SerialMapper;
import com.avacom.erp.modules.almacen.repository.ProductoRepository;
import com.avacom.erp.modules.almacen.repository.SerialRepository;
import com.avacom.erp.modules.almacen.validator.SerialBusinessValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class SerialServiceImpl implements SerialService {

    private final SerialRepository serialRepository;
    private final ProductoRepository productoRepository;
    private final SerialMapper serialMapper;
    private final SerialBusinessValidator validator;

    @Override
    public SerialDto crear(CrearSerialRequest request) {
        validator.validarCrear(request);

        ProductoEntity producto = productoRepository.findById(request.getIdProducto())
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        SerialEntity entity = SerialEntity.builder()
                .numeroSerial(request.getNumeroSerial())
                .estado(request.getEstado())
                .producto(producto)
                .build();

        SerialEntity guardado = serialRepository.save(entity);
        return serialMapper.toDto(guardado);
    }

    @Override
    public SerialDto actualizar(Long id, ActualizarSerialRequest request) {
        SerialEntity existente = serialRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serial no encontrado"));

        validator.validarActualizar(existente, request);

        if (request.getEstado() != null) {
            existente.setEstado(request.getEstado());
        }
        if (request.getIdProducto() != null) {
            ProductoEntity producto = productoRepository.findById(request.getIdProducto())
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
            existente.setProducto(producto);
        }

        SerialEntity guardado = serialRepository.save(existente);
        return serialMapper.toDto(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public SerialDto obtener(Long id) {
        SerialEntity entity = serialRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serial no encontrado"));
        return serialMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SerialDto> listar() {
        return serialRepository.findAll()
                .stream()
                .map(serialMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SerialDto> buscar(SerialFiltroRequest filtro) {
        return serialRepository.findAll()
                .stream()
                .filter(s -> filtro.getIdProducto() == null ||
                        (s.getProducto() != null &&
                                Objects.equals(s.getProducto().getId(), filtro.getIdProducto())))
                .filter(s -> filtro.getEstado() == null ||
                        (s.getEstado() != null &&
                                s.getEstado().equalsIgnoreCase(filtro.getEstado())))
                .filter(s -> filtro.getNumeroSerialContiene() == null ||
                        (s.getNumeroSerial() != null &&
                                s.getNumeroSerial().toLowerCase()
                                        .contains(filtro.getNumeroSerialContiene().toLowerCase())))
                .map(serialMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SerialDto> listarPorProducto(Long idProducto) {
        ProductoEntity producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        return serialRepository.findByProducto(producto)
                .stream()
                .map(serialMapper::toDto)
                .toList();
    }

    @Override
    public SerialDto cambiarEstado(Long id, String nuevoEstado) {
        SerialEntity existente = serialRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serial no encontrado"));

        if (nuevoEstado == null || nuevoEstado.isBlank()) {
            throw new IllegalArgumentException("El nuevo estado no puede ser vacío");
        }

        existente.setEstado(nuevoEstado);
        SerialEntity guardado = serialRepository.save(existente);

        return serialMapper.toDto(guardado);
    }

    @Override
    public void eliminar(Long id) {
        if (!serialRepository.existsById(id)) {
            throw new IllegalArgumentException("Serial no encontrado");
        }
        serialRepository.deleteById(id);
    }
}