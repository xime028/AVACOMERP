package com.avacom.erp.modules.serviciotecnico.service;

import com.avacom.erp.modules.serviciotecnico.dto.CrearDocumentoOrdenRequest;
import com.avacom.erp.modules.serviciotecnico.dto.DocumentoOrdenDto;

import java.util.List;

public interface DocumentoOrdenService {

    DocumentoOrdenDto agregarDocumento(Long idOrden, CrearDocumentoOrdenRequest request);

    List<DocumentoOrdenDto> listarPorOrden(Long idOrden);
}