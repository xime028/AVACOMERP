package com.avacom.erp.modules.almacen.service;

import com.avacom.erp.modules.almacen.dto.StockAlmacenDto;

import java.util.List;

public interface StockAlmacenService {

    StockAlmacenDto configurarStock(Long idProducto, Long idAlmacen, Integer stock, Integer stockReservado);

    List<StockAlmacenDto> listarPorProducto(Long idProducto);

    List<StockAlmacenDto> listarPorAlmacen(Long idAlmacen);
}