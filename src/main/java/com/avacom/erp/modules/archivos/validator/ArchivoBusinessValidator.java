package com.avacom.erp.modules.archivos.validator;

import com.avacom.erp.modules.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class ArchivoBusinessValidator {

    private final UsuarioRepository usuarioRepository;

    private static final Set<String> ESTADOS_VALIDOS = Set.of(
            "ACTIVO", "INACTIVO", "ELIMINADO"
    );

    public void validarUpload(MultipartFile file, Long idUsuarioCargador) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("El archivo está vacío");
        }
        if (file.getOriginalFilename() != null && file.getOriginalFilename().length() > 255) {
            throw new IllegalArgumentException("El nombre del archivo es demasiado largo");
        }
        if (!usuarioRepository.existsById(idUsuarioCargador)) {
            throw new IllegalArgumentException("Usuario cargador no existe");
        }
    }

    public void validarEstado(String estado) {
        if (estado == null || estado.isBlank()) {
            throw new IllegalArgumentException("El estado no puede estar vacío");
        }
        String upper = estado.toUpperCase();
        if (!ESTADOS_VALIDOS.contains(upper)) {
            throw new IllegalArgumentException("Estado de archivo no soportado: " + estado);
        }
    }
}