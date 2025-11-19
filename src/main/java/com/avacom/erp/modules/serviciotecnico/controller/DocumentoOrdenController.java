package com.avacom.erp.modules.serviciotecnico.controller;

import com.avacom.erp.modules.serviciotecnico.dto.CrearDocumentoOrdenRequest;
import com.avacom.erp.modules.serviciotecnico.dto.DocumentoOrdenDto;
import com.avacom.erp.modules.serviciotecnico.service.DocumentoOrdenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicio-tecnico/ordenes/{idOrden}/documentos")
@RequiredArgsConstructor
public class DocumentoOrdenController {

    private final DocumentoOrdenService documentoOrdenService;

    @GetMapping
    public ResponseEntity<List<DocumentoOrdenDto>> listar(@PathVariable Long idOrden) {
        return ResponseEntity.ok(documentoOrdenService.listarPorOrden(idOrden));
    }

    @PostMapping
    public ResponseEntity<DocumentoOrdenDto> agregar(@PathVariable Long idOrden,
                                                     @Valid @RequestBody CrearDocumentoOrdenRequest request) {
        return ResponseEntity.ok(documentoOrdenService.agregarDocumento(idOrden, request));
    }
}