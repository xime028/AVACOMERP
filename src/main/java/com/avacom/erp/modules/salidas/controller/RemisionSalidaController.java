package com.avacom.erp.modules.salidas.controller;

import com.avacom.erp.modules.salidas.dto.CrearRemisionSalidaRequest;
import com.avacom.erp.modules.salidas.dto.RemisionSalidaDto;
import com.avacom.erp.modules.salidas.service.RemisionSalidaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salidas/{idSalida}/remisiones")
@RequiredArgsConstructor
public class RemisionSalidaController {

    private final RemisionSalidaService remisionSalidaService;

    @GetMapping
    public ResponseEntity<List<RemisionSalidaDto>> listar(@PathVariable Long idSalida) {
        return ResponseEntity.ok(remisionSalidaService.listarPorSalida(idSalida));
    }

    @PostMapping
    public ResponseEntity<RemisionSalidaDto> agregar(@PathVariable Long idSalida,
                                                     @Valid @RequestBody CrearRemisionSalidaRequest request) {
        return ResponseEntity.ok(remisionSalidaService.agregarRemision(idSalida, request));
    }
}