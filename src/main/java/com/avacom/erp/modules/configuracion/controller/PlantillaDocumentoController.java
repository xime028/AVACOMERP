package com.avacom.erp.modules.configuracion.controller;

import com.avacom.erp.modules.configuracion.dto.ActualizarPlantillaDocumentoRequest;
import com.avacom.erp.modules.configuracion.dto.PlantillaDocumentoDto;
import com.avacom.erp.modules.configuracion.service.PlantillaDocumentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/configuracion/plantillas")
@RequiredArgsConstructor
public class PlantillaDocumentoController {

    private final PlantillaDocumentoService plantillaDocumentoService;

    @GetMapping
    public ResponseEntity<List<PlantillaDocumentoDto>> listarTodas() {
        return ResponseEntity.ok(plantillaDocumentoService.listarTodas());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<PlantillaDocumentoDto> obtener(@PathVariable String codigo) {
        return ResponseEntity.ok(plantillaDocumentoService.obtenerPorCodigo(codigo));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<PlantillaDocumentoDto> actualizar(@PathVariable String codigo,
                                                            @Valid @RequestBody ActualizarPlantillaDocumentoRequest request) {
        return ResponseEntity.ok(plantillaDocumentoService.actualizar(codigo, request));
    }
}