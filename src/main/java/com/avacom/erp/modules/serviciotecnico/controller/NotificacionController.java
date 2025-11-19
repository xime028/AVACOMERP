package com.avacom.erp.modules.serviciotecnico.controller;

import com.avacom.erp.modules.serviciotecnico.dto.CrearNotificacionRequest;
import com.avacom.erp.modules.serviciotecnico.dto.NotificacionDto;
import com.avacom.erp.modules.serviciotecnico.service.NotificacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicio-tecnico/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionService notificacionService;

    @PostMapping("/orden/{idOrden}")
    public ResponseEntity<NotificacionDto> crearParaOrden(@PathVariable Long idOrden,
                                                          @Valid @RequestBody CrearNotificacionRequest request) {
        return ResponseEntity.ok(notificacionService.crearParaOrden(idOrden, request));
    }

    @GetMapping("/orden/{idOrden}")
    public ResponseEntity<List<NotificacionDto>> listarPorOrden(@PathVariable Long idOrden) {
        return ResponseEntity.ok(notificacionService.listarPorOrden(idOrden));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<NotificacionDto>> listarPorUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(notificacionService.listarPorUsuario(idUsuario));
    }
}