package com.avacom.erp.modules.proveedores.repository;

import com.avacom.erp.modules.proveedores.entity.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProveedorRepository extends JpaRepository<ProveedorEntity, Long> {

    boolean existsByNit(String nit);

    Optional<ProveedorEntity> findByNit(String nit);
}