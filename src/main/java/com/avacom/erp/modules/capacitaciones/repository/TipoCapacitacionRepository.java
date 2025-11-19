package com.avacom.erp.modules.capacitaciones.repository;

import com.avacom.erp.modules.capacitaciones.entity.TipoCapacitacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoCapacitacionRepository extends JpaRepository<TipoCapacitacionEntity, Long> {

    boolean existsByCodigoTipo(String codigoTipo);

    Optional<TipoCapacitacionEntity> findByCodigoTipo(String codigoTipo);
}