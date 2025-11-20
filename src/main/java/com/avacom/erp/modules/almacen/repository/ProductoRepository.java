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

    // Buscar por referencia o nombre
    List<ProductoEntity> findByNombreContainingIgnoreCaseOrReferenciaContainingIgnoreCase(
            String nombre,
            String referencia
    );

    // Búsqueda por referencia o nombre (ignorando mayúsculas/minúsculas)
    List<ProductoEntity> findByReferenciaContainingIgnoreCaseOrNombreContainingIgnoreCase(
            String referencia,
            String nombre
    );

    boolean existsByReferenciaIgnoreCase(String referencia);
}