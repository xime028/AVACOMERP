package com.avacom.erp.modules.clientes.controller;

import com.avacom.erp.modules.clientes.dto.*;
import com.avacom.erp.modules.clientes.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDto>> listarTodos() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDetalleDto> obtenerDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.obtenerDetalle(id));
    }

    @PostMapping
    public ResponseEntity<ClienteDto> crear(@Valid @RequestBody CrearClienteRequest request) {
        return ResponseEntity.ok(clienteService.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> actualizar(@PathVariable Long id,
                                                 @Valid @RequestBody ActualizarClienteRequest request) {
        return ResponseEntity.ok(clienteService.actualizar(id, request));
    }

    @PostMapping("/buscar")
    public ResponseEntity<List<ClienteDto>> buscar(@RequestBody ClienteFiltroRequest filtro) {
        return ResponseEntity.ok(clienteService.buscarPorFiltro(filtro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}