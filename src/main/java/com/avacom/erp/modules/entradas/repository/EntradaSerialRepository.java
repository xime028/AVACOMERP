package com.avacom.erp.modules.entradas.repository;

import com.avacom.erp.modules.entradas.entity.EntradaItemEntity;
import com.avacom.erp.modules.entradas.entity.EntradaSerialEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntradaSerialRepository extends JpaRepository<EntradaSerialEntity, Long> {

    List<EntradaSerialEntity> findByEntradaItem(EntradaItemEntity entradaItem);
}