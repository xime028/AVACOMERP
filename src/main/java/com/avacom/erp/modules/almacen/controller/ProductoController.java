package com.avacom.erp.modules.almacen.controller;

import com.avacom.erp.modules.almacen.dto.*;
import com.avacom.erp.modules.almacen.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/almacen/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDto>> listarTodos() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDetalleDto> obtenerDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerDetalle(id));
    }

    @PostMapping
    public ResponseEntity<ProductoDto> crear(@Valid @RequestBody CrearProductoRequest request) {
        return ResponseEntity.ok(productoService.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> actualizar(@PathVariable Long id,
                                                  @Valid @RequestBody ActualizarProductoRequest request) {
        return ResponseEntity.ok(productoService.actualizar(id, request));
    }

    @PostMapping("/buscar")
    public ResponseEntity<List<ProductoDto>> buscar(@RequestBody ProductoFiltroRequest filtro) {
        return ResponseEntity.ok(productoService.buscarPorFiltro(filtro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}