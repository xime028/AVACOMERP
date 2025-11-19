package com.avacom.erp.modules.almacen.repository;

import com.avacom.erp.modules.almacen.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

    boolean existsByNombre(String nombre);

    Optional<CategoriaEntity> findByNombre(String nombre);

    boolean existsByNombreIgnoreCase(String nombre);
}