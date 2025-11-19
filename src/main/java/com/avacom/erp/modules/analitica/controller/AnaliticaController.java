package com.avacom.erp.modules.analitica.controller;

import com.avacom.erp.modules.analitica.dto.*;
import com.avacom.erp.modules.analitica.service.AnaliticaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analitica")
@RequiredArgsConstructor
public class AnaliticaController {

    private final AnaliticaService analiticaService;

    /**
     * Resumen general del sistema (totales globales).
     */
    @GetMapping("/resumen-general")
    public ResponseEntity<ResumenGeneralDto> obtenerResumenGeneral() {
        return ResponseEntity.ok(analiticaService.obtenerResumenGeneral());
    }

    /**
     * KPI de inventario (productos / stock).
     */
    @GetMapping("/inventario")
    public ResponseEntity<KpiInventarioDto> obtenerKpiInventario() {
        return ResponseEntity.ok(analiticaService.obtenerKpiInventario());
    }

    /**
     * KPI de entradas/salidas para un rango de fechas.
     * Si no se envía rango, usa los últimos 30 días.
     */
    @PostMapping("/entradas-salidas")
    public ResponseEntity<KpiEntradasSalidasDto> obtenerKpiEntradasSalidas(
            @RequestBody(required = false) AnaliticaFiltroFechaRequest filtro) {
        return ResponseEntity.ok(analiticaService.obtenerKpiEntradasSalidas(filtro));
    }

    /**
     * KPI de servicio técnico (ordenes de servicio) para un rango.
     */
    @PostMapping("/servicio-tecnico")
    public ResponseEntity<KpiServicioTecnicoDto> obtenerKpiServicioTecnico(
            @RequestBody(required = false) AnaliticaFiltroFechaRequest filtro) {
        return ResponseEntity.ok(analiticaService.obtenerKpiServicioTecnico(filtro));
    }

    /**
     * KPI de usuarios + auditoría para un rango de fechas.
     */
    @PostMapping("/usuarios-actividad")
    public ResponseEntity<KpiUsuariosActividadDto> obtenerKpiUsuariosActividad(
            @RequestBody(required = false) AnaliticaFiltroFechaRequest filtro) {
        return ResponseEntity.ok(analiticaService.obtenerKpiUsuariosActividad(filtro));
    }
}