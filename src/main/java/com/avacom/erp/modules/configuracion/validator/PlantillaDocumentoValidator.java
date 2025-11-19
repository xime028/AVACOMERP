package com.avacom.erp.modules.configuracion.validator;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PlantillaDocumentoValidator {

    // Códigos permitidos en esta versión
    private static final Set<String> CODIGOS_VALIDOS = Set.of(
            "REMISION_ENTRADA",
            "REMISION_SALIDA",
            "ORDEN_SERVICIO"
    );

    public void validarCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Código de plantilla vacío");
        }
        if (!CODIGOS_VALIDOS.contains(codigo)) {
            throw new IllegalArgumentException("Código de plantilla no soportado: " + codigo);
        }
    }
}