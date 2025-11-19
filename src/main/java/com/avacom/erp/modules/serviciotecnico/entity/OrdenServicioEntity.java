package com.avacom.erp.modules.serviciotecnico.entity;

import com.avacom.erp.modules.clientes.entity.ClienteEntity;
import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import com.avacom.erp.modules.almacen.entity.SerialEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "ordenes_servicios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenServicioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "consecutivo", unique = true, nullable = false)
    private Integer consecutivo;

    @Column(name = "descripcion_dano", length = 200)
    private String descripcionDano;

    @Column(name = "estado", length = 30, nullable = false)
    private String estado;

    @Column(name = "observaciones", columnDefinition = "text")
    private String observacionesGenerales;

    @Column(name = "prioridad", length = 50)
    private String prioridad;

    @Column(name = "fecha_ingreso", nullable = false)
    private OffsetDateTime fechaIngreso;

    @Column(name = "fecha_actualizacion", nullable = false)
    private OffsetDateTime fechaActualizacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private ClienteEntity cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tecnico")
    private UsuarioEntity tecnico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_serial")
    private SerialEntity serial;

    // FK simples a tablas que aún no modelamos como entidades
    @Column(name = "id_falla")
    private Long idFalla;

    @Column(name = "id_solucion")
    private Long idSolucion;

    @Column(name = "id_archivo")
    private Long idArchivo;

    @OneToMany(mappedBy = "ordenServicio", fetch = FetchType.LAZY)
    private List<ObservacionOrdenEntity> observaciones;

    @OneToMany(mappedBy = "ordenServicio", fetch = FetchType.LAZY)
    private List<DocumentoOrdenEntity> documentos;

    @OneToMany(mappedBy = "ordenServicio", fetch = FetchType.LAZY)
    private List<NotificacionEntity> notificaciones;
}