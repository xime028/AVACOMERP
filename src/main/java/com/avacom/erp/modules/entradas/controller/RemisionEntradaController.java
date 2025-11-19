package com.avacom.erp.modules.entradas.controller;

import com.avacom.erp.modules.entradas.dto.CrearRemisionEntradaRequest;
import com.avacom.erp.modules.entradas.dto.RemisionEntradaDto;
import com.avacom.erp.modules.entradas.service.RemisionEntradaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entradas/{idEntrada}/remisiones")
@RequiredArgsConstructor
public class RemisionEntradaController {

    private final RemisionEntradaService remisionEntradaService;

    @GetMapping
    public ResponseEntity<List<RemisionEntradaDto>> listar(@PathVariable Long idEntrada) {
        return ResponseEntity.ok(remisionEntradaService.listarPorEntrada(idEntrada));
    }

    @PostMapping
    public ResponseEntity<RemisionEntradaDto> agregar(@PathVariable Long idEntrada,
                                                      @Valid @RequestBody CrearRemisionEntradaRequest request) {
        return ResponseEntity.ok(remisionEntradaService.agregarRemision(idEntrada, request));
    }
}