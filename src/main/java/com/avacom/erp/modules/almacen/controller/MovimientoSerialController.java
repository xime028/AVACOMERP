package com.avacom.erp.modules.almacen.controller;

import com.avacom.erp.modules.almacen.dto.MovimientoSerialDto;
import com.avacom.erp.modules.almacen.dto.MovimientoSerialFiltroRequest;
import com.avacom.erp.modules.almacen.service.MovimientoSerialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/almacen/movimientos-serial")
@RequiredArgsConstructor
public class MovimientoSerialController {

    private final MovimientoSerialService movimientoSerialService;

    @PostMapping("/buscar")
    public ResponseEntity<List<MovimientoSerialDto>> buscar(@RequestBody MovimientoSerialFiltroRequest filtro) {
        return ResponseEntity.ok(movimientoSerialService.buscar(filtro));
    }
}