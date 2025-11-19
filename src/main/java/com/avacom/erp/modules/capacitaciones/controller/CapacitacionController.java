package com.avacom.erp.modules.capacitaciones.controller;

import com.avacom.erp.modules.capacitaciones.dto.*;
import com.avacom.erp.modules.capacitaciones.service.CapacitacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/capacitaciones")
@RequiredArgsConstructor
public class CapacitacionController {

    private final CapacitacionService capacitacionService;

    @GetMapping
    public ResponseEntity<List<CapacitacionDto>> listar() {
        return ResponseEntity.ok(capacitacionService.listar());
    }

    @PostMapping("/buscar")
    public ResponseEntity<List<CapacitacionDto>> buscar(@RequestBody CapacitacionFiltroRequest filtro) {
        return ResponseEntity.ok(capacitacionService.buscarPorFiltro(filtro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CapacitacionDetalleDto> obtenerDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(capacitacionService.obtenerDetalle(id));
    }

    @PostMapping
    public ResponseEntity<CapacitacionDto> crear(@Valid @RequestBody CrearCapacitacionRequest request) {
        return ResponseEntity.ok(capacitacionService.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CapacitacionDto> actualizar(@PathVariable Long id,
                                                      @Valid @RequestBody ActualizarCapacitacionRequest request) {
        return ResponseEntity.ok(capacitacionService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        capacitacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}