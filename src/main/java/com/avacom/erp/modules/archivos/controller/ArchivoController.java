package com.avacom.erp.modules.archivos.controller;

import com.avacom.erp.modules.archivos.dto.*;
import com.avacom.erp.modules.archivos.service.ArchivoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api/archivos")
@RequiredArgsConstructor
public class ArchivoController {

    private final ArchivoService archivoService;

    /**
     * Subir archivo (multipart/form-data)
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ArchivoDto> subirArchivo(@RequestParam("file") MultipartFile file,
                                                   @RequestParam("idUsuarioCargador") Long idUsuarioCargador) {
        return ResponseEntity.ok(archivoService.subirArchivo(file, idUsuarioCargador));
    }

    /**
     * Obtener solo metadata del archivo.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ArchivoDto> obtenerMetadata(@PathVariable Long id) {
        return ResponseEntity.ok(archivoService.obtenerMetadata(id));
    }

    /**
     * Buscar archivos por filtros.
     */
    @PostMapping("/buscar")
    public ResponseEntity<List<ArchivoDto>> buscar(@RequestBody ArchivoFiltroRequest filtro) {
        return ResponseEntity.ok(archivoService.buscar(filtro));
    }

    /**
     * Descargar el archivo físico.
     */
    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> descargar(@PathVariable Long id) {
        ArchivoContenidoDto contenido = archivoService.descargar(id);

        String fileName = contenido.getNombreOriginal() != null
                ? contenido.getNombreOriginal()
                : "archivo";

        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename*=UTF-8''" + encodedFileName)
                .contentType(contenido.getTipoContenido() != null
                        ? MediaType.parseMediaType(contenido.getTipoContenido())
                        : MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(contenido.getTamanioBytes() != null
                        ? contenido.getTamanioBytes()
                        : contenido.getContenido().length)
                .body(contenido.getContenido());
    }

    /**
     * Cambiar estado del archivo.
     */
    @PatchMapping("/{id}/estado")
    public ResponseEntity<ArchivoDto> actualizarEstado(@PathVariable Long id,
                                                       @Valid @RequestBody ActualizarEstadoArchivoRequest request) {
        return ResponseEntity.ok(archivoService.actualizarEstado(id, request.getEstado()));
    }
}