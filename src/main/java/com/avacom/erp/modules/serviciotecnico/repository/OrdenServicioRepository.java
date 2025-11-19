package com.avacom.erp.modules.serviciotecnico.repository;

import com.avacom.erp.modules.serviciotecnico.entity.OrdenServicioEntity;
import com.avacom.erp.modules.clientes.entity.ClienteEntity;
import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdenServicioRepository extends JpaRepository<OrdenServicioEntity, Long> {

    Optional<OrdenServicioEntity> findByConsecutivo(Integer consecutivo);

    List<OrdenServicioEntity> findByCliente(ClienteEntity cliente);

    List<OrdenServicioEntity> findByTecnico(UsuarioEntity tecnico);

    List<OrdenServicioEntity> findByEstado(String estado);
}