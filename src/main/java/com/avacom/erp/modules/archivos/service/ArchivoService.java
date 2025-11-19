package com.avacom.erp.modules.archivos.service;

import com.avacom.erp.modules.archivos.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArchivoService {

    /**
     * Sube un archivo usando almacenamiento local (pensado para poder migrar a S3 después).
     */
    ArchivoDto subirArchivo(MultipartFile file, Long idUsuarioCargador);

    /**
     * Obtiene solo metadata del archivo.
     */
    ArchivoDto obtenerMetadata(Long idArchivo);

    /**
     * Descarga contenido del archivo.
     */
    ArchivoContenidoDto descargar(Long idArchivo);

    /**
     * Busca archivos aplicando filtros simples en memoria.
     */
    List<ArchivoDto> buscar(ArchivoFiltroRequest filtro);

    /**
     * Cambia el estado del archivo (ACTIVO, INACTIVO, ELIMINADO).
     */
    ArchivoDto actualizarEstado(Long idArchivo, String nuevoEstado);
}