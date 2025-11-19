package com.avacom.erp.modules.garantias.controller;

import com.avacom.erp.modules.garantias.dto.*;
import com.avacom.erp.modules.garantias.service.GarantiaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/garantias")
@RequiredArgsConstructor
public class GarantiaController {

    private final GarantiaService garantiaService;

    @GetMapping
    public ResponseEntity<List<GarantiaDto>> listar() {
        return ResponseEntity.ok(garantiaService.listar());
    }

    @PostMapping("/buscar")
    public ResponseEntity<List<GarantiaDto>> buscar(@RequestBody GarantiaFiltroRequest filtro) {
        return ResponseEntity.ok(garantiaService.buscar(filtro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GarantiaDetalleDto> obtenerDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(garantiaService.obtenerDetalle(id));
    }

    @PostMapping
    public ResponseEntity<GarantiaDto> crear(@Valid @RequestBody CrearGarantiaRequest request) {
        return ResponseEntity.ok(garantiaService.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GarantiaDto> actualizar(@PathVariable Long id,
                                                  @Valid @RequestBody ActualizarGarantiaRequest request) {
        return ResponseEntity.ok(garantiaService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        garantiaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}