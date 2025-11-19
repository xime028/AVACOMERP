package com.avacom.erp.modules.auditoria.validator;

import com.avacom.erp.modules.auditoria.dto.RegistrarAuditoriaRequest;
import com.avacom.erp.modules.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class AuditoriaBusinessValidator {

    private final UsuarioRepository usuarioRepository;

    // Puedes ajustarlos luego si quieres formalizar las acciones válidas
    private static final Set<String> ACCIONES_VALIDAS = Set.of(
            "CREATE", "UPDATE", "DELETE", "LOGIN", "LOGOUT", "ERROR", "OTHER"
    );

    public void validarRegistrar(RegistrarAuditoriaRequest request) {
        if (!usuarioRepository.existsById(request.getIdUsuario())) {
            throw new IllegalArgumentException("Usuario no existe para auditoría");
        }

        String accion = request.getAccion() != null ? request.getAccion().toUpperCase() : null;
        if (accion == null || accion.isBlank()) {
            throw new IllegalArgumentException("Acción de auditoría requerida");
        }

        // No obligamos a que esté en ACCIONES_VALIDAS, solo avisamos
        if (!ACCIONES_VALIDAS.contains(accion)) {
            // Si quieres, podrías lanzar excepción, de momento solo permitimos libre.
        }

        if (request.getModulo() != null && request.getModulo().length() > 40) {
            throw new IllegalArgumentException("El nombre del módulo excede la longitud permitida");
        }

        if (request.getEntidad() != null && request.getEntidad().length() > 60) {
            throw new IllegalArgumentException("El nombre de la entidad excede la longitud permitida");
        }
    }
}