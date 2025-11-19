package com.avacom.erp.modules.auditoria.repository;

import com.avacom.erp.modules.auditoria.entity.AuditoriaDetalleEntity;
import com.avacom.erp.modules.auditoria.entity.AuditoriaEventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditoriaDetalleRepository extends JpaRepository<AuditoriaDetalleEntity, Long> {

    List<AuditoriaDetalleEntity> findByEvento(AuditoriaEventoEntity evento);
}