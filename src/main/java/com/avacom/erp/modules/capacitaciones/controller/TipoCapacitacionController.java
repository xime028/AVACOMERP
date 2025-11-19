package com.avacom.erp.modules.capacitaciones.controller;

import com.avacom.erp.modules.capacitaciones.dto.*;
import com.avacom.erp.modules.capacitaciones.service.TipoCapacitacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/capacitaciones/tipos")
@RequiredArgsConstructor
public class TipoCapacitacionController {

    private final TipoCapacitacionService tipoCapacitacionService;

    @GetMapping
    public ResponseEntity<List<TipoCapacitacionDto>> listar() {
        return ResponseEntity.ok(tipoCapacitacionService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoCapacitacionDto> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(tipoCapacitacionService.obtener(id));
    }

    @PostMapping
    public ResponseEntity<TipoCapacitacionDto> crear(@Valid @RequestBody CrearTipoCapacitacionRequest request) {
        return ResponseEntity.ok(tipoCapacitacionService.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoCapacitacionDto> actualizar(@PathVariable Long id,
                                                          @Valid @RequestBody ActualizarTipoCapacitacionRequest request) {
        return ResponseEntity.ok(tipoCapacitacionService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tipoCapacitacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}