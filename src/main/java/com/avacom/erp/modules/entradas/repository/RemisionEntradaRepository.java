package com.avacom.erp.modules.entradas.repository;

import com.avacom.erp.modules.entradas.entity.EntradaEntity;
import com.avacom.erp.modules.entradas.entity.RemisionEntradaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RemisionEntradaRepository extends JpaRepository<RemisionEntradaEntity, Long> {

    List<RemisionEntradaEntity> findByEntradaOrderByFechaAsc(EntradaEntity entrada);
}