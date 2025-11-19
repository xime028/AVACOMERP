package com.avacom.erp.modules.configuracion.controller;

import com.avacom.erp.modules.configuracion.dto.ActualizarEmpresaRequest;
import com.avacom.erp.modules.configuracion.dto.EmpresaDto;
import com.avacom.erp.modules.configuracion.service.EmpresaConfigService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/configuracion/empresa")
@RequiredArgsConstructor
public class EmpresaConfigController {

    private final EmpresaConfigService empresaConfigService;

    @GetMapping
    public ResponseEntity<EmpresaDto> obtener() {
        return ResponseEntity.ok(empresaConfigService.obtener());
    }

    @PutMapping
    public ResponseEntity<EmpresaDto> actualizar(@Valid @RequestBody ActualizarEmpresaRequest request) {
        return ResponseEntity.ok(empresaConfigService.actualizar(request));
    }
}