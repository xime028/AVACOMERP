package com.avacom.erp.modules.serviciotecnico.controller;

import com.avacom.erp.modules.serviciotecnico.dto.CrearObservacionRequest;
import com.avacom.erp.modules.serviciotecnico.dto.ObservacionDto;
import com.avacom.erp.modules.serviciotecnico.service.ObservacionOrdenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicio-tecnico/ordenes/{idOrden}/observaciones")
@RequiredArgsConstructor
public class ObservacionOrdenController {

    private final ObservacionOrdenService observacionOrdenService;

    @GetMapping
    public ResponseEntity<List<ObservacionDto>> listar(@PathVariable Long idOrden) {
        return ResponseEntity.ok(observacionOrdenService.listarPorOrden(idOrden));
    }

    @PostMapping
    public ResponseEntity<ObservacionDto> agregar(@PathVariable Long idOrden,
                                                  @Valid @RequestBody CrearObservacionRequest request) {
        return ResponseEntity.ok(observacionOrdenService.agregarObservacion(idOrden, request));
    }
}
