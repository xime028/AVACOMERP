package com.avacom.erp.modules.almacen.repository;

import com.avacom.erp.modules.almacen.entity.MovimientoSerialEntity;
import com.avacom.erp.modules.almacen.entity.SerialEntity;
import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface MovimientoSerialRepository extends JpaRepository<MovimientoSerialEntity, Long> {

    List<MovimientoSerialEntity> findBySerial(SerialEntity serial);

    List<MovimientoSerialEntity> findByUsuario(UsuarioEntity usuario);

    List<MovimientoSerialEntity> findByFechaBetween(OffsetDateTime desde, OffsetDateTime hasta);
}