package com.avacom.erp.modules.almacen.controller;

import com.avacom.erp.modules.almacen.service.ProductoService;
import com.avacom.erp.modules.almacen.service.SerialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/almacen/productos")
@RequiredArgsConstructor
public class SerialPageController {

    private final SerialService serialService;
    private final ProductoService productoService;

    @GetMapping("/{id}/seriales")
    public String verSerialesProducto(@PathVariable Long id, Model model) {
        var producto = productoService.obtenerPorId(id);
        var seriales = serialService.obtenerPorProducto(id);

        model.addAttribute("producto", producto);
        model.addAttribute("seriales", seriales);
        return "almacen/seriales/verSeriales";
    }
}