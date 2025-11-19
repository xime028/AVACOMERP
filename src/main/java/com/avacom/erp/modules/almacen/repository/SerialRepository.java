package com.avacom.erp.modules.almacen.repository;

import com.avacom.erp.modules.almacen.entity.SerialEntity;
import com.avacom.erp.modules.almacen.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SerialRepository extends JpaRepository<SerialEntity, Long> {

    boolean existsByNumeroSerial(String numeroSerial);

    Optional<SerialEntity> findByNumeroSerial(String numeroSerial);

    List<SerialEntity> findByProducto(ProductoEntity producto);
}