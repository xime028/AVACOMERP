package com.avacom.erp.modules.auditoria.repository;

import com.avacom.erp.modules.auditoria.entity.AuditoriaEventoEntity;
import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface AuditoriaEventoRepository extends JpaRepository<AuditoriaEventoEntity, Long> {

    List<AuditoriaEventoEntity> findByUsuario(UsuarioEntity usuario);

    List<AuditoriaEventoEntity> findByModulo(String modulo);

    List<AuditoriaEventoEntity> findByEntidad(String entidad);

    List<AuditoriaEventoEntity> findByFechaBetween(OffsetDateTime desde, OffsetDateTime hasta);
}