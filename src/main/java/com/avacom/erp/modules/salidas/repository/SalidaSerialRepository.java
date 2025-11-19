package com.avacom.erp.modules.salidas.repository;

import com.avacom.erp.modules.salidas.entity.SalidaItemEntity;
import com.avacom.erp.modules.salidas.entity.SalidaSerialEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalidaSerialRepository extends JpaRepository<SalidaSerialEntity, Long> {

    List<SalidaSerialEntity> findBySalidaItem(SalidaItemEntity salidaItem);
}