package com.avacom.erp.modules.salidas.repository;

import com.avacom.erp.modules.salidas.entity.SalidaEntity;
import com.avacom.erp.modules.clientes.entity.ClienteEntity;
import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SalidaRepository extends JpaRepository<SalidaEntity, Long> {

    Optional<SalidaEntity> findByConsecutivo(String consecutivo);

    List<SalidaEntity> findByCliente(ClienteEntity cliente);

    List<SalidaEntity> findByUsuario(UsuarioEntity usuario);
}