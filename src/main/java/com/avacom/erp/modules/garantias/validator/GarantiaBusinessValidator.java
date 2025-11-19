package com.avacom.erp.modules.garantias.validator;

import com.avacom.erp.modules.garantias.dto.ActualizarGarantiaRequest;
import com.avacom.erp.modules.garantias.dto.CrearGarantiaRequest;
import com.avacom.erp.modules.garantias.entity.GarantiaEntity;
import com.avacom.erp.modules.garantias.repository.GarantiaRepository;
import com.avacom.erp.modules.almacen.repository.SerialRepository;
import com.avacom.erp.modules.archivos.repository.ArchivoRepository;
import com.avacom.erp.modules.clientes.repository.ClienteRepository;
import com.avacom.erp.modules.proveedores.repository.ProveedorRepository;
import com.avacom.erp.modules.serviciotecnico.repository.OrdenServicioRepository;
import com.avacom.erp.modules.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class GarantiaBusinessValidator {

    private final GarantiaRepository garantiaRepository;
    private final SerialRepository serialRepository;
    private final ClienteRepository clienteRepository;
    private final ProveedorRepository proveedorRepository;
    private final OrdenServicioRepository ordenServicioRepository;
    private final ArchivoRepository archivoRepository;
    private final UsuarioRepository usuarioRepository;

    private static final Set<String> ESTADOS_VALIDOS = Set.of(
            "ACTIVA", "VENCIDA", "ANULADA"
    );

    public void validarCrear(CrearGarantiaRequest request) {
        if (!serialRepository.existsById(request.getIdSerial())) {
            throw new IllegalArgumentException("El serial no existe");
        }
        if (!clienteRepository.existsById(request.getIdCliente())) {
            throw new IllegalArgumentException("El cliente no existe");
        }
        if (!proveedorRepository.existsById(request.getIdProveedor())) {
            throw new IllegalArgumentException("El proveedor no existe");
        }
        if (request.getIdOrdenServicio() != null &&
                !ordenServicioRepository.existsById(request.getIdOrdenServicio())) {
            throw new IllegalArgumentException("La orden de servicio no existe");
        }
        if (request.getIdArchivo() != null &&
                !archivoRepository.existsById(request.getIdArchivo())) {
            throw new IllegalArgumentException("El archivo asociado no existe");
        }
        if (!usuarioRepository.existsById(request.getIdUsuario())) {
            throw new IllegalArgumentException("El usuario no existe");
        }

        if (garantiaRepository.existsByNumeroDocumento(request.getNumeroDocumento())) {
            throw new IllegalArgumentException("Ya existe una garantía con ese número de documento");
        }

        validarFechas(request.getFechaInicio(), request.getFechaFin());
        validarEstado(request.getEstado());
    }

    public void validarActualizar(GarantiaEntity existente, ActualizarGarantiaRequest request) {
        if (request.getIdSerial() != null && !serialRepository.existsById(request.getIdSerial())) {
            throw new IllegalArgumentException("El serial no existe");
        }
        if (request.getIdCliente() != null && !clienteRepository.existsById(request.getIdCliente())) {
            throw new IllegalArgumentException("El cliente no existe");
        }
        if (request.getIdProveedor() != null && !proveedorRepository.existsById(request.getIdProveedor())) {
            throw new IllegalArgumentException("El proveedor no existe");
        }
        if (request.getIdOrdenServicio() != null &&
                !ordenServicioRepository.existsById(request.getIdOrdenServicio())) {
            throw new IllegalArgumentException("La orden de servicio no existe");
        }
        if (request.getIdArchivo() != null &&
                !archivoRepository.existsById(request.getIdArchivo())) {
            throw new IllegalArgumentException("El archivo asociado no existe");
        }

        if (request.getNumeroDocumento() != null &&
                !request.getNumeroDocumento().equals(existente.getNumeroDocumento()) &&
                garantiaRepository.existsByNumeroDocumento(request.getNumeroDocumento())) {
            throw new IllegalArgumentException("Ya existe una garantía con ese número de documento");
        }

        LocalDate inicio = request.getFechaInicio() != null ? request.getFechaInicio() : existente.getFechaInicio();
        LocalDate fin = request.getFechaFin() != null ? request.getFechaFin() : existente.getFechaFin();
        validarFechas(inicio, fin);

        if (request.getEstado() != null) {
            validarEstado(request.getEstado());
        }
    }

    private void validarFechas(LocalDate inicio, LocalDate fin) {
        if (inicio == null || fin == null) {
            throw new IllegalArgumentException("Las fechas de inicio y fin son obligatorias");
        }
        if (inicio.isAfter(fin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }
    }

    private void validarEstado(String estado) {
        if (estado == null || estado.isBlank()) {
            throw new IllegalArgumentException("El estado de la garantía es obligatorio");
        }
        String upper = estado.toUpperCase();
        if (!ESTADOS_VALIDOS.contains(upper)) {
            // si quieres forzar los estados, puedes lanzar excepción
            // por ahora simplemente permitimos otros valores
        }
    }
}