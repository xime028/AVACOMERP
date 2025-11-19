package com.avacom.erp.modules.configuracion.repository;

import com.avacom.erp.modules.configuracion.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {

    Optional<EmpresaEntity> findTopByOrderByIdAsc();

    boolean existsByNit(String nit);
}