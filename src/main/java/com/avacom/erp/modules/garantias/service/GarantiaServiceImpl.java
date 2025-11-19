package com.avacom.erp.modules.garantias.service;

import com.avacom.erp.modules.garantias.dto.*;
import com.avacom.erp.modules.garantias.entity.GarantiaEntity;
import com.avacom.erp.modules.garantias.mapper.GarantiaMapper;
import com.avacom.erp.modules.garantias.repository.GarantiaRepository;
import com.avacom.erp.modules.garantias.validator.GarantiaBusinessValidator;
import com.avacom.erp.modules.almacen.entity.SerialEntity;
import com.avacom.erp.modules.almacen.repository.SerialRepository;
import com.avacom.erp.modules.archivos.entity.ArchivoEntity;
import com.avacom.erp.modules.archivos.repository.ArchivoRepository;
import com.avacom.erp.modules.clientes.entity.ClienteEntity;
import com.avacom.erp.modules.clientes.repository.ClienteRepository;
import com.avacom.erp.modules.proveedores.entity.ProveedorEntity;
import com.avacom.erp.modules.proveedores.repository.ProveedorRepository;
import com.avacom.erp.modules.serviciotecnico.entity.OrdenServicioEntity;
import com.avacom.erp.modules.serviciotecnico.repository.OrdenServicioRepository;
import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import com.avacom.erp.modules.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class GarantiaServiceImpl implements GarantiaService {

    private final GarantiaRepository garantiaRepository;
    private final SerialRepository serialRepository;
    private final ClienteRepository clienteRepository;
    private final ProveedorRepository proveedorRepository;
    private final OrdenServicioRepository ordenServicioRepository;
    private final ArchivoRepository archivoRepository;
    private final UsuarioRepository usuarioRepository;
    private final GarantiaMapper garantiaMapper;
    private final GarantiaBusinessValidator validator;

    @Override
    public GarantiaDto crear(CrearGarantiaRequest request) {
        validator.validarCrear(request);

        SerialEntity serial = serialRepository.findById(request.getIdSerial())
                .orElseThrow(() -> new IllegalArgumentException("Serial no encontrado"));

        ClienteEntity cliente = clienteRepository.findById(request.getIdCliente())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        ProveedorEntity proveedor = proveedorRepository.findById(request.getIdProveedor())
                .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado"));

        OrdenServicioEntity ordenServicio = null;
        if (request.getIdOrdenServicio() != null) {
            ordenServicio = ordenServicioRepository.findById(request.getIdOrdenServicio())
                    .orElseThrow(() -> new IllegalArgumentException("Orden de servicio no encontrada"));
        }

        ArchivoEntity archivo = null;
        if (request.getIdArchivo() != null) {
            archivo = archivoRepository.findById(request.getIdArchivo())
                    .orElseThrow(() -> new IllegalArgumentException("Archivo no encontrado"));
        }

        UsuarioEntity usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        GarantiaEntity entity = GarantiaEntity.builder()
                .serial(serial)
                .cliente(cliente)
                .proveedor(proveedor)
                .ordenServicio(ordenServicio)
                .tipoGarantia(request.getTipoGarantia())
                .numeroDocumento(request.getNumeroDocumento())
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .condiciones(request.getCondiciones())
                .estado(request.getEstado())
                .archivo(archivo)
                .fechaRegistro(OffsetDateTime.now())
                .usuario(usuario)
                .build();

        GarantiaEntity guardada = garantiaRepository.save(entity);
        return garantiaMapper.toDto(guardada);
    }

    @Override
    public GarantiaDto actualizar(Long id, ActualizarGarantiaRequest request) {
        GarantiaEntity existente = garantiaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Garantía no encontrada"));

        validator.validarActualizar(existente, request);

        if (request.getIdSerial() != null) {
            SerialEntity serial = serialRepository.findById(request.getIdSerial())
                    .orElseThrow(() -> new IllegalArgumentException("Serial no encontrado"));
            existente.setSerial(serial);
        }
        if (request.getIdCliente() != null) {
            ClienteEntity cliente = clienteRepository.findById(request.getIdCliente())
                    .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
            existente.setCliente(cliente);
        }
        if (request.getIdProveedor() != null) {
            ProveedorEntity proveedor = proveedorRepository.findById(request.getIdProveedor())
                    .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado"));
            existente.setProveedor(proveedor);
        }
        if (request.getIdOrdenServicio() != null) {
            OrdenServicioEntity os = ordenServicioRepository.findById(request.getIdOrdenServicio())
                    .orElseThrow(() -> new IllegalArgumentException("Orden de servicio no encontrada"));
            existente.setOrdenServicio(os);
        }
        if (request.getTipoGarantia() != null) {
            existente.setTipoGarantia(request.getTipoGarantia());
        }
        if (request.getNumeroDocumento() != null) {
            existente.setNumeroDocumento(request.getNumeroDocumento());
        }
        if (request.getFechaInicio() != null) {
            existente.setFechaInicio(request.getFechaInicio());
        }
        if (request.getFechaFin() != null) {
            existente.setFechaFin(request.getFechaFin());
        }
        if (request.getCondiciones() != null) {
            existente.setCondiciones(request.getCondiciones());
        }
        if (request.getEstado() != null) {
            existente.setEstado(request.getEstado());
        }
        if (request.getIdArchivo() != null) {
            ArchivoEntity archivo = archivoRepository.findById(request.getIdArchivo())
                    .orElseThrow(() -> new IllegalArgumentException("Archivo no encontrado"));
            existente.setArchivo(archivo);
        }

        GarantiaEntity guardada = garantiaRepository.save(existente);
        return garantiaMapper.toDto(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public GarantiaDetalleDto obtenerDetalle(Long id) {
        GarantiaEntity e = garantiaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Garantía no encontrada"));

        return GarantiaDetalleDto.builder()
                .id(e.getId())
                .idSerial(e.getSerial() != null ? e.getSerial().getId() : null)
                .numeroSerial(e.getSerial() != null ? e.getSerial().getNumeroSerial() : null)
                .idProducto(e.getSerial() != null && e.getSerial().getProducto() != null
                        ? e.getSerial().getProducto().getId() : null)
                .referenciaProducto(e.getSerial() != null && e.getSerial().getProducto() != null
                        ? e.getSerial().getProducto().getReferencia() : null)
                .nombreProducto(e.getSerial() != null && e.getSerial().getProducto() != null
                        ? e.getSerial().getProducto().getNombre() : null)
                .idCliente(e.getCliente() != null ? e.getCliente().getId() : null)
                .nombreCliente(e.getCliente() != null ? e.getCliente().getNombre() : null)
                .correoCliente(e.getCliente() != null ? e.getCliente().getCorreo() : null)
                .telefonoCliente(e.getCliente() != null ? e.getCliente().getTelefono() : null)
                .idProveedor(e.getProveedor() != null ? e.getProveedor().getId() : null)
                .nombreProveedor(e.getProveedor() != null ? e.getProveedor().getNombre() : null)
                .correoProveedor(e.getProveedor() != null ? e.getProveedor().getCorreo() : null)
                .telefonoProveedor(e.getProveedor() != null ? e.getProveedor().getTelefono() : null)
                .idOrdenServicio(e.getOrdenServicio() != null ? e.getOrdenServicio().getId() : null)
                .consecutivoOrdenServicio(e.getOrdenServicio() != null ? e.getOrdenServicio().getConsecutivo() : null)
                .estadoOrdenServicio(e.getOrdenServicio() != null ? e.getOrdenServicio().getEstado() : null)
                .tipoGarantia(e.getTipoGarantia())
                .numeroDocumento(e.getNumeroDocumento())
                .fechaInicio(e.getFechaInicio())
                .fechaFin(e.getFechaFin())
                .condiciones(e.getCondiciones())
                .estado(e.getEstado())
                .idArchivo(e.getArchivo() != null ? e.getArchivo().getId() : null)
                .nombreArchivoOriginal(e.getArchivo() != null ? e.getArchivo().getNombreOriginal() : null)
                .idUsuario(e.getUsuario() != null ? e.getUsuario().getId() : null)
                .nombreUsuario(e.getUsuario() != null ? e.getUsuario().getNombre() : null)
                .correoUsuario(e.getUsuario() != null ? e.getUsuario().getCorreo() : null)
                .fechaRegistro(e.getFechaRegistro())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<GarantiaDto> listar() {
        return garantiaRepository.findAll()
                .stream()
                .map(garantiaMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<GarantiaDto> buscar(GarantiaFiltroRequest filtro) {
        return garantiaRepository.findAll()
                .stream()
                .filter(g -> filtro.getIdSerial() == null ||
                        (g.getSerial() != null &&
                                Objects.equals(g.getSerial().getId(), filtro.getIdSerial())))
                .filter(g -> filtro.getNumeroSerial() == null ||
                        (g.getSerial() != null && g.getSerial().getNumeroSerial() != null &&
                                g.getSerial().getNumeroSerial().equalsIgnoreCase(filtro.getNumeroSerial())))
                .filter(g -> filtro.getIdCliente() == null ||
                        (g.getCliente() != null &&
                                Objects.equals(g.getCliente().getId(), filtro.getIdCliente())))
                .filter(g -> filtro.getIdProveedor() == null ||
                        (g.getProveedor() != null &&
                                Objects.equals(g.getProveedor().getId(), filtro.getIdProveedor())))
                .filter(g -> filtro.getIdOrdenServicio() == null ||
                        (g.getOrdenServicio() != null &&
                                Objects.equals(g.getOrdenServicio().getId(), filtro.getIdOrdenServicio())))
                .filter(g -> filtro.getTipoGarantia() == null ||
                        (g.getTipoGarantia() != null &&
                                g.getTipoGarantia().equalsIgnoreCase(filtro.getTipoGarantia())))
                .filter(g -> filtro.getNumeroDocumento() == null ||
                        (g.getNumeroDocumento() != null &&
                                g.getNumeroDocumento().equalsIgnoreCase(filtro.getNumeroDocumento())))
                .filter(g -> filtro.getEstado() == null ||
                        (g.getEstado() != null &&
                                g.getEstado().equalsIgnoreCase(filtro.getEstado())))
                .filter(g -> filtro.getFechaInicioDesde() == null ||
                        (g.getFechaInicio() != null &&
                                !g.getFechaInicio().isBefore(filtro.getFechaInicioDesde())))
                .filter(g -> filtro.getFechaFinHasta() == null ||
                        (g.getFechaFin() != null &&
                                !g.getFechaFin().isAfter(filtro.getFechaFinHasta())))
                .map(garantiaMapper::toDto)
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        if (!garantiaRepository.existsById(id)) {
            throw new IllegalArgumentException("Garantía no encontrada");
        }
        garantiaRepository.deleteById(id);
    }
}