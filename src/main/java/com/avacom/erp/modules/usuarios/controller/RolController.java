package com.avacom.erp.modules.usuarios.controller;

import com.avacom.erp.modules.usuarios.dto.RolDto;
import com.avacom.erp.modules.usuarios.service.RolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios/roles")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;

    @GetMapping
    public ResponseEntity<List<RolDto>> listar() {
        return ResponseEntity.ok(rolService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(rolService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<RolDto> crear(@Valid @RequestBody RolDto dto) {
        return ResponseEntity.ok(rolService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDto> actualizar(@PathVariable Long id,
                                             @Valid @RequestBody RolDto dto) {
        return ResponseEntity.ok(rolService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        rolService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}