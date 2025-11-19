package com.avacom.erp.modules.serviciotecnico.repository;

import com.avacom.erp.modules.serviciotecnico.entity.ObservacionOrdenEntity;
import com.avacom.erp.modules.serviciotecnico.entity.OrdenServicioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObservacionOrdenRepository extends JpaRepository<ObservacionOrdenEntity, Long> {

    List<ObservacionOrdenEntity> findByOrdenServicioOrderByFechaAsc(OrdenServicioEntity ordenServicio);
}