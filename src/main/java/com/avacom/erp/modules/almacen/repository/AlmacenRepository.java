package com.avacom.erp.modules.almacen.repository;

import com.avacom.erp.modules.almacen.entity.AlmacenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlmacenRepository extends JpaRepository<AlmacenEntity, Long> {
    boolean existsByCodigoIgnoreCase(String codigo);
    Optional<AlmacenEntity> findByCodigoIgnoreCase(String codigo);
}