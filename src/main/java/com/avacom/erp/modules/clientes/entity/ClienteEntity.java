package com.avacom.erp.modules.clientes.entity;

import com.avacom.erp.modules.archivos.entity.ArchivoEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identificacion", length = 50, unique = true, nullable = false)
    private String identificacion;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "correo", length = 100, unique = true)
    private String correo;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "direccion", length = 100)
    private String direccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grupo")
    private GrupoEntity grupo;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "fecha_registro", nullable = false)
    private OffsetDateTime fechaRegistro;

    @Column(name = "tipo_cliente", length = 50)
    private String tipoCliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_archivo")
    private ArchivoEntity archivo;


}