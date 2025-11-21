package com.avacom.erp.modules.almacen.controller;

import com.avacom.erp.modules.almacen.dto.CrearSerialRequest;
import com.avacom.erp.modules.almacen.dto.SerialDto;
import com.avacom.erp.modules.almacen.service.SerialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/almacen/seriales")
@RequiredArgsConstructor
public class SerialController {

    private final SerialService serialService;

    @PostMapping
    public ResponseEntity<SerialDto> crear(@Valid @RequestBody CrearSerialRequest request) {
        return ResponseEntity.ok(serialService.crear(request));
    }

    @PatchMapping("/{idSerial}/estado")
    public ResponseEntity<Void> cambiarEstado(@PathVariable Long idSerial,
                                              @RequestParam("estado") String nuevoEstado) {
        serialService.cambiarEstado(idSerial, nuevoEstado);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/producto/{idProducto}/disponibles")
    public ResponseEntity<List<SerialDto>> listarDisponiblesPorProducto(@PathVariable Long idProducto) {
        return ResponseEntity.ok(serialService.listarDisponiblesPorProducto(idProducto));
    }

    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<List<SerialDto>> listarSerialesPorProducto(@PathVariable Long idProducto) {
        return ResponseEntity.ok(serialService.obtenerPorProducto(idProducto));
    }
}