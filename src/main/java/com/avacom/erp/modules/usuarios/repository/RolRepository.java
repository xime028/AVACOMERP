package com.avacom.erp.modules.usuarios.repository;

import com.avacom.erp.modules.usuarios.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<RolEntity, Long> {

    boolean existsByNombre(String nombre);

    Optional<RolEntity> findByNombre(String nombre);
}