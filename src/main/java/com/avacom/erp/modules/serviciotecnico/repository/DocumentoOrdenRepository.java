package com.avacom.erp.modules.serviciotecnico.repository;

import com.avacom.erp.modules.serviciotecnico.entity.DocumentoOrdenEntity;
import com.avacom.erp.modules.serviciotecnico.entity.OrdenServicioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentoOrdenRepository extends JpaRepository<DocumentoOrdenEntity, Long> {

    List<DocumentoOrdenEntity> findByOrdenServicioOrderByFechaAsc(OrdenServicioEntity ordenServicio);
}