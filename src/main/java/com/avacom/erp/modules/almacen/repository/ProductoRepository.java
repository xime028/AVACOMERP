package com.avacom.erp.modules.almacen.repository;

import com.avacom.erp.modules.almacen.entity.ProductoEntity;
import com.avacom.erp.modules.almacen.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {

    boolean existsByReferencia(String referencia);

    Optional<ProductoEntity> findByReferencia(String referencia);

    List<ProductoEntity> findByNombreContainingIgnoreCase(String nombre);

    List<ProductoEntity> findByCategoria(CategoriaEntity categoria);
}