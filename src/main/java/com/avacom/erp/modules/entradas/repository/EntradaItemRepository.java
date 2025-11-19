package com.avacom.erp.modules.entradas.repository;

import com.avacom.erp.modules.entradas.entity.EntradaEntity;
import com.avacom.erp.modules.entradas.entity.EntradaItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntradaItemRepository extends JpaRepository<EntradaItemEntity, Long> {

    List<EntradaItemEntity> findByEntrada(EntradaEntity entrada);
}