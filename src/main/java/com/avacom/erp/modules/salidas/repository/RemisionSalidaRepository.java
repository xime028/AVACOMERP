package com.avacom.erp.modules.salidas.repository;

import com.avacom.erp.modules.salidas.entity.RemisionSalidaEntity;
import com.avacom.erp.modules.salidas.entity.SalidaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RemisionSalidaRepository extends JpaRepository<RemisionSalidaEntity, Long> {

    List<RemisionSalidaEntity> findBySalidaOrderByFechaAsc(SalidaEntity salida);
}