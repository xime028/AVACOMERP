package com.avacom.erp.modules.entradas.controller;

import com.avacom.erp.modules.entradas.dto.*;
import com.avacom.erp.modules.entradas.service.EntradaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entradas")
@RequiredArgsConstructor
public class EntradaController {

    private final EntradaService entradaService;

    @GetMapping
    public ResponseEntity<List<EntradaDto>> listar() {
        return ResponseEntity.ok(entradaService.listarTodos());
    }

    @PostMapping("/buscar")
    public ResponseEntity<List<EntradaDto>> buscar(@RequestBody EntradaFiltroRequest filtro) {
        return ResponseEntity.ok(entradaService.buscarPorFiltro(filtro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntradaDetalleDto> obtenerDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(entradaService.obtenerDetalle(id));
    }

    @PostMapping
    public ResponseEntity<EntradaDto> crear(@Valid @RequestBody CrearEntradaRequest request) {
        return ResponseEntity.ok(entradaService.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntradaDto> actualizar(@PathVariable Long id,
                                                 @Valid @RequestBody ActualizarEntradaRequest request) {
        return ResponseEntity.ok(entradaService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        entradaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}