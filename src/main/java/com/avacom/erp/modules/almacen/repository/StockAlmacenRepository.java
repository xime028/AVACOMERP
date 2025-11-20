package com.avacom.erp.modules.almacen.repository;

import com.avacom.erp.modules.almacen.entity.AlmacenEntity;
import com.avacom.erp.modules.almacen.entity.ProductoEntity;
import com.avacom.erp.modules.almacen.entity.StockAlmacenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockAlmacenRepository extends JpaRepository<StockAlmacenEntity, Long> {

    Optional<StockAlmacenEntity> findByProductoAndAlmacen(ProductoEntity producto, AlmacenEntity almacen);

    List<StockAlmacenEntity> findByProducto(ProductoEntity producto);

    List<StockAlmacenEntity> findByAlmacen(AlmacenEntity almacen);
}