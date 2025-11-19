package com.avacom.erp.modules.archivos.entity;

import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "archivos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArchivoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_archivo")
    private Long id;

    @Column(name = "bucket", length = 128, nullable = false)
    private String bucket;

    @Column(name = "clave_objeto", length = 512, nullable = false)
    private String claveObjeto;

    @Column(name = "id_version", length = 150)
    private String idVersion;

    @Column(name = "nombre_original", length = 255, nullable = false)
    private String nombreOriginal;

    @Column(name = "tipo_contenido", length = 120)
    private String tipoContenido;

    @Column(name = "tamanio_bytes")
    private Long tamanioBytes;

    @Column(name = "etag", length = 64)
    private String etag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_cargador", nullable = false)
    private UsuarioEntity usuarioCargador;

    @Column(name = "estado", length = 16, nullable = false)
    private String estado;

    @Column(name = "fecha_creacion", nullable = false)
    private OffsetDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private OffsetDateTime fechaActualizacion;
}
