package com.avacom.erp.modules.archivos.repository;

import com.avacom.erp.modules.archivos.entity.ArchivoEntity;
import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface ArchivoRepository extends JpaRepository<ArchivoEntity, Long> {

    Optional<ArchivoEntity> findByBucketAndClaveObjeto(String bucket, String claveObjeto);

    List<ArchivoEntity> findByUsuarioCargador(UsuarioEntity usuario);

    List<ArchivoEntity> findByEstado(String estado);

    List<ArchivoEntity> findByFechaCreacionBetween(OffsetDateTime desde, OffsetDateTime hasta);
}