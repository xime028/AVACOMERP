package com.avacom.erp.modules.garantias.repository;

import com.avacom.erp.modules.garantias.entity.GarantiaEntity;
import com.avacom.erp.modules.almacen.entity.SerialEntity;
import com.avacom.erp.modules.clientes.entity.ClienteEntity;
import com.avacom.erp.modules.proveedores.entity.ProveedorEntity;
import com.avacom.erp.modules.serviciotecnico.entity.OrdenServicioEntity;
import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface GarantiaRepository extends JpaRepository<GarantiaEntity, Long> {

    boolean existsByNumeroDocumento(String numeroDocumento);

    Optional<GarantiaEntity> findByNumeroDocumento(String numeroDocumento);

    List<GarantiaEntity> findBySerial(SerialEntity serial);

    List<GarantiaEntity> findByCliente(ClienteEntity cliente);

    List<GarantiaEntity> findByProveedor(ProveedorEntity proveedor);

    List<GarantiaEntity> findByOrdenServicio(OrdenServicioEntity ordenServicio);

    List<GarantiaEntity> findByUsuario(UsuarioEntity usuario);

    List<GarantiaEntity> findByEstadoIgnoreCase(String estado);

    List<GarantiaEntity> findByFechaInicioGreaterThanEqualAndFechaFinLessThanEqual(LocalDate desde, LocalDate hasta);
}