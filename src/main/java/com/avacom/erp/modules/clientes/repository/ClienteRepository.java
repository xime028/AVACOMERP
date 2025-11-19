package com.avacom.erp.modules.clientes.repository;

import com.avacom.erp.modules.clientes.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findByIdentificacion(String identificacion);

    Optional<ClienteEntity> findByCorreo(String correo);

    boolean existsByIdentificacion(String identificacion);

    boolean existsByCorreo(String correo);

    List<ClienteEntity> findByNombreContainingIgnoreCase(String nombre);
}