package com.avacom.erp.modules.capacitaciones.repository;

import com.avacom.erp.modules.capacitaciones.entity.CapacitacionEntity;
import com.avacom.erp.modules.capacitaciones.entity.TipoCapacitacionEntity;
import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface CapacitacionRepository extends JpaRepository<CapacitacionEntity, Long> {

    List<CapacitacionEntity> findByTipoCapacitacion(TipoCapacitacionEntity tipoCapacitacion);

    List<CapacitacionEntity> findByUsuario(UsuarioEntity usuario);

    List<CapacitacionEntity> findByFechaBetween(OffsetDateTime desde, OffsetDateTime hasta);
}