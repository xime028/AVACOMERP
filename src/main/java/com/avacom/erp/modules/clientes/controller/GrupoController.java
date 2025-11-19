package com.avacom.erp.modules.clientes.controller;

import com.avacom.erp.modules.clientes.dto.GrupoDto;
import com.avacom.erp.modules.clientes.service.GrupoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes/grupos")
@RequiredArgsConstructor
public class GrupoController {

    private final GrupoService grupoService;

    @GetMapping
    public ResponseEntity<List<GrupoDto>> listarTodos() {
        return ResponseEntity.ok(grupoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrupoDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(grupoService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<GrupoDto> crear(@Valid @RequestBody GrupoDto dto) {
        return ResponseEntity.ok(grupoService.crear(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        grupoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}