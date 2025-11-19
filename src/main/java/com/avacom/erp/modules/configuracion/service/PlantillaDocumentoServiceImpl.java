//Implementación en memoria: lista de plantillas en un ConcurrentHashMap. Funciona ya mismo sin tocar BD, y luego si quieres lo migramos a una tabla.
package com.avacom.erp.modules.configuracion.service;

import com.avacom.erp.modules.configuracion.dto.ActualizarPlantillaDocumentoRequest;
import com.avacom.erp.modules.configuracion.dto.PlantillaDocumentoDto;
import com.avacom.erp.modules.configuracion.validator.PlantillaDocumentoValidator;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class PlantillaDocumentoServiceImpl implements PlantillaDocumentoService {

    private final PlantillaDocumentoValidator validator;

    /**
     * Almacén en memoria de plantillas:
     * clave = código (REMISION_SALIDA, etc.)
     */
    private final Map<String, PlantillaDocumentoDto> plantillas = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        // Plantillas por defecto muy simples (texto o HTML)
        plantillas.put("REMISION_ENTRADA", PlantillaDocumentoDto.builder()
                .codigo("REMISION_ENTRADA")
                .nombre("Remisión de Entrada")
                .contenido("""
                        REMISIÓN DE ENTRADA
                        Empresa: {{empresaNombre}}
                        Proveedor: {{proveedorNombre}}
                        Fecha: {{fecha}}
                        Consecutivo: {{consecutivo}}
                        
                        Items:
                        {{tablaItems}}
                        """)
                .build());

        plantillas.put("REMISION_SALIDA", PlantillaDocumentoDto.builder()
                .codigo("REMISION_SALIDA")
                .nombre("Remisión de Salida")
                .contenido("""
                        REMISIÓN DE SALIDA
                        Empresa: {{empresaNombre}}
                        Cliente: {{clienteNombre}}
                        Fecha: {{fecha}}
                        Consecutivo: {{consecutivo}}
                        
                        Items:
                        {{tablaItems}}
                        """)
                .build());

        plantillas.put("ORDEN_SERVICIO", PlantillaDocumentoDto.builder()
                .codigo("ORDEN_SERVICIO")
                .nombre("Orden de Servicio")
                .contenido("""
                        ORDEN DE SERVICIO
                        Empresa: {{empresaNombre}}
                        Cliente: {{clienteNombre}}
                        Consecutivo: {{consecutivo}}
                        Fecha ingreso: {{fechaIngreso}}
                        Técnico: {{tecnicoNombre}}
                        
                        Descripción del daño:
                        {{descripcionDano}}
                        
                        Observaciones:
                        {{observaciones}}
                        """)
                .build());
    }

    @Override
    public List<PlantillaDocumentoDto> listarTodas() {
        return plantillas.values().stream()
                .sorted(Comparator.comparing(PlantillaDocumentoDto::getCodigo))
                .toList();
    }

    @Override
    public PlantillaDocumentoDto obtenerPorCodigo(String codigo) {
        validator.validarCodigo(codigo);

        PlantillaDocumentoDto plantilla = plantillas.get(codigo);
        if (plantilla == null) {
            throw new IllegalArgumentException("Plantilla no encontrada: " + codigo);
        }
        return plantilla;
    }

    @Override
    public PlantillaDocumentoDto actualizar(String codigo, ActualizarPlantillaDocumentoRequest request) {
        validator.validarCodigo(codigo);

        PlantillaDocumentoDto existente = plantillas.get(codigo);
        if (existente == null) {
            // Si no existía, la creamos con nombre = código
            existente = PlantillaDocumentoDto.builder()
                    .codigo(codigo)
                    .nombre(codigo)
                    .contenido(request.getContenido())
                    .build();
        } else {
            existente.setContenido(request.getContenido());
        }

        plantillas.put(codigo, existente);
        return existente;
    }
}