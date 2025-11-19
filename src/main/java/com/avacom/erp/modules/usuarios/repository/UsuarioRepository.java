package com.avacom.erp.modules.usuarios.repository;

import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    boolean existsByUsuario(String usuario);

    boolean existsByCorreo(String correo);

    Optional<UsuarioEntity> findByUsuario(String usuario);

    Optional<UsuarioEntity> findByCorreo(String correo);
}