package com.avacom.erp.modules.salidas.repository;

import com.avacom.erp.modules.salidas.entity.SalidaEntity;
import com.avacom.erp.modules.salidas.entity.SalidaItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalidaItemRepository extends JpaRepository<SalidaItemEntity, Long> {

    List<SalidaItemEntity> findBySalida(SalidaEntity salida);
}