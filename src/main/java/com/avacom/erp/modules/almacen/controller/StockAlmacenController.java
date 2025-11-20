package com.avacom.erp.modules.almacen.controller;

import com.avacom.erp.modules.almacen.dto.StockAlmacenDto;
import com.avacom.erp.modules.almacen.service.StockAlmacenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/almacen/stock")
@RequiredArgsConstructor
public class StockAlmacenController {

    private final StockAlmacenService stockAlmacenService;

    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<List<StockAlmacenDto>> listarPorProducto(@PathVariable Long idProducto) {
        return ResponseEntity.ok(stockAlmacenService.listarPorProducto(idProducto));
    }

    @GetMapping("/almacen/{idAlmacen}")
    public ResponseEntity<List<StockAlmacenDto>> listarPorAlmacen(@PathVariable Long idAlmacen) {
        return ResponseEntity.ok(stockAlmacenService.listarPorAlmacen(idAlmacen));
    }

    @PostMapping("/configurar")
    public ResponseEntity<StockAlmacenDto> configurar(
            @RequestParam Long idProducto,
            @RequestParam Long idAlmacen,
            @RequestParam(required = false) Integer stock,
            @RequestParam(required = false) Integer stockReservado) {

        return ResponseEntity.ok(stockAlmacenService.configurarStock(idProducto, idAlmacen, stock, stockReservado));
    }
}