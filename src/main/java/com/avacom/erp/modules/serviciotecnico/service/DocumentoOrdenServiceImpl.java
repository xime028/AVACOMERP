package com.avacom.erp.modules.serviciotecnico.service;

import com.avacom.erp.modules.serviciotecnico.dto.CrearDocumentoOrdenRequest;
import com.avacom.erp.modules.serviciotecnico.dto.DocumentoOrdenDto;
import com.avacom.erp.modules.serviciotecnico.entity.DocumentoOrdenEntity;
import com.avacom.erp.modules.serviciotecnico.entity.OrdenServicioEntity;
import com.avacom.erp.modules.serviciotecnico.mapper.DocumentoOrdenMapper;
import com.avacom.erp.modules.serviciotecnico.repository.DocumentoOrdenRepository;
import com.avacom.erp.modules.serviciotecnico.repository.OrdenServicioRepository;
import com.avacom.erp.modules.serviciotecnico.validator.DocumentoOrdenBusinessValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DocumentoOrdenServiceImpl implements DocumentoOrdenService {

    private final DocumentoOrdenRepository documentoOrdenRepository;
    private final OrdenServicioRepository ordenServicioRepository;
    private final DocumentoOrdenMapper documentoOrdenMapper;
    private final DocumentoOrdenBusinessValidator validator;

    @Override
    public DocumentoOrdenDto agregarDocumento(Long idOrden, CrearDocumentoOrdenRequest request) {
        OrdenServicioEntity orden = ordenServicioRepository.findById(idOrden)
                .orElseThrow(() -> new IllegalArgumentException("Orden de servicio no encontrada"));

        validator.validarAgregar(orden);

        Boolean firmado = request.getFirmado() != null ? request.getFirmado() : Boolean.FALSE;

        DocumentoOrdenEntity entity = DocumentoOrdenEntity.builder()
                .ordenServicio(orden)
                .idDocumento(request.getIdDocumento())
                .firmado(firmado)
                .idFirmaDocumento(request.getIdFirmaDocumento())
                .hashFuncional(request.getHashFuncional())
                .fecha(OffsetDateTime.now())
                .build();

        DocumentoOrdenEntity guardado = documentoOrdenRepository.save(entity);
        return documentoOrdenMapper.toDto(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentoOrdenDto> listarPorOrden(Long idOrden) {
        OrdenServicioEntity orden = ordenServicioRepository.findById(idOrden)
                .orElseThrow(() -> new IllegalArgumentException("Orden de servicio no encontrada"));

        return documentoOrdenRepository.findByOrdenServicioOrderByFechaAsc(orden)
                .stream()
                .map(documentoOrdenMapper::toDto)
                .toList();
    }
}