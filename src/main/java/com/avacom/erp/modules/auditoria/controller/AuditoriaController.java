package com.avacom.erp.modules.auditoria.controller;

import com.avacom.erp.modules.auditoria.dto.*;
import com.avacom.erp.modules.auditoria.service.AuditoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auditoria")
@RequiredArgsConstructor
public class AuditoriaController {

    private final AuditoriaService auditoriaService;

    /**
     * Registrar un evento de auditoría.
     * Este endpoint es útil para pruebas, pero normalmente llamarás al servicio desde otros módulos.
     */
    @PostMapping("/eventos")
    public ResponseEntity<AuditoriaEventoDto> registrar(@Valid @RequestBody RegistrarAuditoriaRequest request) {
        return ResponseEntity.ok(auditoriaService.registrar(request));
    }

    /**
     * Obtener un evento con sus detalles.
     */
    @GetMapping("/eventos/{id}")
    public ResponseEntity<AuditoriaEventoDetalleDto> obtenerDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(auditoriaService.obtenerDetalle(id));
    }

    /**
     * Buscar eventos aplicando filtros.
     */
    @PostMapping("/eventos/buscar")
    public ResponseEntity<List<AuditoriaEventoDto>> buscar(@RequestBody AuditoriaFiltroRequest filtro) {
        return ResponseEntity.ok(auditoriaService.buscar(filtro));
    }

    /**
     * Listar los últimos N eventos (por defecto 50).
     */
    @GetMapping("/eventos/ultimos")
    public ResponseEntity<List<AuditoriaEventoDto>> listarUltimos(
            @RequestParam(name = "limite", required = false, defaultValue = "50") int limite) {
        return ResponseEntity.ok(auditoriaService.listarUltimos(limite));
    }
}