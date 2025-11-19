package com.avacom.erp.modules.archivos.service;

import com.avacom.erp.modules.archivos.dto.*;
import com.avacom.erp.modules.archivos.entity.ArchivoEntity;
import com.avacom.erp.modules.archivos.mapper.ArchivoMapper;
import com.avacom.erp.modules.archivos.repository.ArchivoRepository;
import com.avacom.erp.modules.archivos.validator.ArchivoBusinessValidator;
import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import com.avacom.erp.modules.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ArchivoServiceImpl implements ArchivoService {

    private final ArchivoRepository archivoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ArchivoMapper archivoMapper;
    private final ArchivoBusinessValidator validator;

    @Value("${app.storage.base-path:uploads}")
    private String basePath;

    @Value("${app.storage.default-bucket:local}")
    private String defaultBucket;

    @Override
    public ArchivoDto subirArchivo(MultipartFile file, Long idUsuarioCargador) {
        validator.validarUpload(file, idUsuarioCargador);

        UsuarioEntity usuario = usuarioRepository.findById(idUsuarioCargador)
                .orElseThrow(() -> new IllegalArgumentException("Usuario cargador no encontrado"));

        String bucket = defaultBucket;

        // Subcarpeta por fecha, para organizar
        String fechaFolder = DateTimeFormatter.ofPattern("yyyyMMdd").format(java.time.LocalDate.now());

        String originalFilename = file.getOriginalFilename() != null
                ? file.getOriginalFilename()
                : "archivo";

        String extension = "";
        int dotIdx = originalFilename.lastIndexOf('.');
        if (dotIdx > 0 && dotIdx < originalFilename.length() - 1) {
            extension = originalFilename.substring(dotIdx);
        }

        String randomName = UUID.randomUUID().toString().replace("-", "");
        String storedFileName = randomName + extension;

        String claveObjeto = fechaFolder + "/" + storedFileName;

        Path bucketPath = Paths.get(basePath, bucket);
        Path folderPath = bucketPath.resolve(fechaFolder);
        Path fullPath = folderPath.resolve(storedFileName);

        try {
            Files.createDirectories(folderPath);
            Files.copy(file.getInputStream(), fullPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Error guardando archivo en el sistema de archivos", e);
        }

        OffsetDateTime ahora = OffsetDateTime.now();

        ArchivoEntity entity = ArchivoEntity.builder()
                .bucket(bucket)
                .claveObjeto(claveObjeto)
                .idVersion(UUID.randomUUID().toString())
                .nombreOriginal(originalFilename)
                .tipoContenido(file.getContentType())
                .tamanioBytes(file.getSize())
                .etag(randomName)  // se puede cambiar luego a un MD5 real si es necesario
                .usuarioCargador(usuario)
                .estado("ACTIVO")
                .fechaCreacion(ahora)
                .fechaActualizacion(ahora)
                .build();

        ArchivoEntity guardado = archivoRepository.save(entity);
        return archivoMapper.toDto(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public ArchivoDto obtenerMetadata(Long idArchivo) {
        ArchivoEntity entity = archivoRepository.findById(idArchivo)
                .orElseThrow(() -> new IllegalArgumentException("Archivo no encontrado"));
        return archivoMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public ArchivoContenidoDto descargar(Long idArchivo) {
        ArchivoEntity entity = archivoRepository.findById(idArchivo)
                .orElseThrow(() -> new IllegalArgumentException("Archivo no encontrado"));

        Path fullPath = Paths.get(basePath, entity.getBucket(), entity.getClaveObjeto());

        if (!Files.exists(fullPath)) {
            throw new IllegalStateException("El archivo físico no existe en el sistema de archivos");
        }

        byte[] bytes;
        try {
            bytes = Files.readAllBytes(fullPath);
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo archivo desde el sistema de archivos", e);
        }

        return ArchivoContenidoDto.builder()
                .id(entity.getId())
                .nombreOriginal(entity.getNombreOriginal())
                .tipoContenido(entity.getTipoContenido())
                .tamanioBytes(entity.getTamanioBytes())
                .contenido(bytes)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArchivoDto> buscar(ArchivoFiltroRequest filtro) {
        return archivoRepository.findAll()
                .stream()
                .filter(a -> filtro.getEstado() == null ||
                        (a.getEstado() != null && a.getEstado().equalsIgnoreCase(filtro.getEstado())))
                .filter(a -> filtro.getTipoContenido() == null ||
                        (a.getTipoContenido() != null && a.getTipoContenido().equalsIgnoreCase(filtro.getTipoContenido())))
                .filter(a -> filtro.getIdUsuarioCargador() == null ||
                        (a.getUsuarioCargador() != null &&
                                Objects.equals(a.getUsuarioCargador().getId(), filtro.getIdUsuarioCargador())))
                .filter(a -> filtro.getNombreOriginalContiene() == null ||
                        (a.getNombreOriginal() != null &&
                                a.getNombreOriginal().toLowerCase()
                                        .contains(filtro.getNombreOriginalContiene().toLowerCase())))
                .filter(a -> filtro.getFechaDesde() == null ||
                        (a.getFechaCreacion() != null && !a.getFechaCreacion().isBefore(filtro.getFechaDesde())))
                .filter(a -> filtro.getFechaHasta() == null ||
                        (a.getFechaCreacion() != null && !a.getFechaCreacion().isAfter(filtro.getFechaHasta())))
                .map(archivoMapper::toDto)
                .toList();
    }

    @Override
    public ArchivoDto actualizarEstado(Long idArchivo, String nuevoEstado) {
        ArchivoEntity entity = archivoRepository.findById(idArchivo)
                .orElseThrow(() -> new IllegalArgumentException("Archivo no encontrado"));

        validator.validarEstado(nuevoEstado);
        entity.setEstado(nuevoEstado.toUpperCase());
        entity.setFechaActualizacion(OffsetDateTime.now());

        ArchivoEntity guardado = archivoRepository.save(entity);
        return archivoMapper.toDto(guardado);
    }
}