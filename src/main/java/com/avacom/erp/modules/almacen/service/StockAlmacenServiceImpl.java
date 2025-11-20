package com.avacom.erp.modules.almacen.service;

import com.avacom.erp.modules.almacen.dto.StockAlmacenDto;
import com.avacom.erp.modules.almacen.entity.AlmacenEntity;
import com.avacom.erp.modules.almacen.entity.ProductoEntity;
import com.avacom.erp.modules.almacen.entity.StockAlmacenEntity;
import com.avacom.erp.modules.almacen.repository.AlmacenRepository;
import com.avacom.erp.modules.almacen.repository.ProductoRepository;
import com.avacom.erp.modules.almacen.repository.StockAlmacenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StockAlmacenServiceImpl implements StockAlmacenService {

    private final StockAlmacenRepository stockAlmacenRepository;
    private final ProductoRepository productoRepository;
    private final AlmacenRepository almacenRepository;

    @Override
    public StockAlmacenDto configurarStock(Long idProducto, Long idAlmacen, Integer stock, Integer stockReservado) {
        ProductoEntity producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        AlmacenEntity almacen = almacenRepository.findById(idAlmacen)
                .orElseThrow(() -> new IllegalArgumentException("Almacén no encontrado"));

        StockAlmacenEntity entity = stockAlmacenRepository.findByProductoAndAlmacen(producto, almacen)
                .orElse(StockAlmacenEntity.builder()
                        .producto(producto)
                        .almacen(almacen)
                        .stock(0)
                        .stockReservado(0)
                        .build());

        if (stock != null) {
            entity.setStock(stock);
        }
        if (stockReservado != null) {
            entity.setStockReservado(stockReservado);
        }

        StockAlmacenEntity guardado = stockAlmacenRepository.save(entity);
        return toDto(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StockAlmacenDto> listarPorProducto(Long idProducto) {
        ProductoEntity producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        return stockAlmacenRepository.findByProducto(producto)
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<StockAlmacenDto> listarPorAlmacen(Long idAlmacen) {
        AlmacenEntity almacen = almacenRepository.findById(idAlmacen)
                .orElseThrow(() -> new IllegalArgumentException("Almacén no encontrado"));

        return stockAlmacenRepository.findByAlmacen(almacen)
                .stream()
                .map(this::toDto)
                .toList();
    }

    private StockAlmacenDto toDto(StockAlmacenEntity e) {
        return StockAlmacenDto.builder()
                .id(e.getId())
                .idProducto(e.getProducto().getId())
                .referenciaProducto(e.getProducto().getReferencia())
                .nombreProducto(e.getProducto().getNombre())
                .idAlmacen(e.getAlmacen().getId())
                .codigoAlmacen(e.getAlmacen().getCodigo())
                .nombreAlmacen(e.getAlmacen().getNombre())
                .stock(e.getStock())
                .stockReservado(e.getStockReservado())
                .build();
    }
}