package com.avacom.erp.modules.serviciotecnico.controller;

import com.avacom.erp.modules.serviciotecnico.dto.*;
import com.avacom.erp.modules.serviciotecnico.service.OrdenServicioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicio-tecnico/ordenes")
@RequiredArgsConstructor
public class OrdenServicioController {

    private final OrdenServicioService ordenServicioService;

    @GetMapping
    public ResponseEntity<List<OrdenServicioDto>> listarTodos() {
        return ResponseEntity.ok(ordenServicioService.listarTodos());
    }

    @PostMapping("/buscar")
    public ResponseEntity<List<OrdenServicioDto>> buscar(@RequestBody OrdenServicioFiltroRequest filtro) {
        return ResponseEntity.ok(ordenServicioService.buscarPorFiltro(filtro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenServicioDetalleDto> obtenerDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(ordenServicioService.obtenerDetalle(id));
    }

    @PostMapping
    public ResponseEntity<OrdenServicioDto> crear(@Valid @RequestBody CrearOrdenServicioRequest request) {
        return ResponseEntity.ok(ordenServicioService.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenServicioDto> actualizar(@PathVariable Long id,
                                                       @Valid @RequestBody ActualizarOrdenServicioRequest request) {
        return ResponseEntity.ok(ordenServicioService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ordenServicioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}