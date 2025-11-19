package com.avacom.erp.modules.garantias.entity;

import com.avacom.erp.modules.almacen.entity.SerialEntity;
import com.avacom.erp.modules.archivos.entity.ArchivoEntity;
import com.avacom.erp.modules.clientes.entity.ClienteEntity;
import com.avacom.erp.modules.proveedores.entity.ProveedorEntity;
import com.avacom.erp.modules.serviciotecnico.entity.OrdenServicioEntity;
import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "garantias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GarantiaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK serial
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_serial", nullable = false)
    private SerialEntity serial;

    // FK cliente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private ClienteEntity cliente;

    // FK proveedor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proveedor", nullable = false)
    private ProveedorEntity proveedor;

    // FK orden de servicio (puede ser opcional)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden_servicio")
    private OrdenServicioEntity ordenServicio;

    @Column(name = "tipo_garantia", length = 50, nullable = false)
    private String tipoGarantia;

    @Column(name = "numero_documento", length = 50, nullable = false, unique = true)
    private String numeroDocumento;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "condiciones", columnDefinition = "text")
    private String condiciones;

    @Column(name = "estado", length = 30, nullable = false)
    private String estado;

    // Documento de soporte de la garantía (opcional)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_archivo")
    private ArchivoEntity archivo;

    @Column(name = "fecha_registro", nullable = false)
    private OffsetDateTime fechaRegistro;

    // Usuario que crea la garantía
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuario;
}