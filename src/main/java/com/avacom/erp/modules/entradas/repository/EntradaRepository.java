package com.avacom.erp.modules.entradas.repository;

import com.avacom.erp.modules.entradas.entity.EntradaEntity;
import com.avacom.erp.modules.proveedores.entity.ProveedorEntity;
import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EntradaRepository extends JpaRepository<EntradaEntity, Long> {

    Optional<EntradaEntity> findByConsecutivo(String consecutivo);

    List<EntradaEntity> findByProveedor(ProveedorEntity proveedor);

    List<EntradaEntity> findByUsuario(UsuarioEntity usuario);
}