package com.avacom.erp.modules.serviciotecnico.repository;

import com.avacom.erp.modules.serviciotecnico.entity.NotificacionEntity;
import com.avacom.erp.modules.serviciotecnico.entity.OrdenServicioEntity;
import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacionRepository extends JpaRepository<NotificacionEntity, Long> {

    List<NotificacionEntity> findByOrdenServicioOrderByFechaDesc(OrdenServicioEntity ordenServicio);

    List<NotificacionEntity> findByUsuarioOrderByFechaDesc(UsuarioEntity usuario);
}