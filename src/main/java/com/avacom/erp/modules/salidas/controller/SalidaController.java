package com.avacom.erp.modules.salidas.controller;

import com.avacom.erp.modules.salidas.dto.*;
import com.avacom.erp.modules.salidas.service.SalidaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salidas")
@RequiredArgsConstructor
public class SalidaController {

    private final SalidaService salidaService;

    @GetMapping
    public ResponseEntity<List<SalidaDto>> listar() {
        return ResponseEntity.ok(salidaService.listarTodos());
    }

    @PostMapping("/buscar")
    public ResponseEntity<List<SalidaDto>> buscar(@RequestBody SalidaFiltroRequest filtro) {
        return ResponseEntity.ok(salidaService.buscarPorFiltro(filtro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalidaDetalleDto> obtenerDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(salidaService.obtenerDetalle(id));
    }

    @PostMapping
    public ResponseEntity<SalidaDto> crear(@Valid @RequestBody CrearSalidaRequest request) {
        return ResponseEntity.ok(salidaService.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalidaDto> actualizar(@PathVariable Long id,
                                                @Valid @RequestBody ActualizarSalidaRequest request) {
        return ResponseEntity.ok(salidaService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        salidaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}