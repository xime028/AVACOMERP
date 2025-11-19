package com.avacom.erp.modules.clientes.repository;

import com.avacom.erp.modules.clientes.entity.GrupoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GrupoRepository extends JpaRepository<GrupoEntity, Long> {

    boolean existsByNombre(String nombre);

    Optional<GrupoEntity> findByNombre(String nombre);
}