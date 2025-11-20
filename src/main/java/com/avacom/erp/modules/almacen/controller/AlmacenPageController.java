package com.avacom.erp.modules.almacen.controller;

import com.avacom.erp.modules.almacen.dto.ActualizarProductoRequest;
import com.avacom.erp.modules.almacen.dto.CategoriaDto;
import com.avacom.erp.modules.almacen.dto.CrearProductoRequest;
import com.avacom.erp.modules.almacen.dto.ProductoDto;
import com.avacom.erp.modules.almacen.service.CategoriaService;
import com.avacom.erp.modules.almacen.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/almacen")
@RequiredArgsConstructor
public class AlmacenPageController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    @GetMapping("/ping")
    @ResponseBody
    public String ping() {
        return "Almacén OK";
    }

    @GetMapping
    public String redirectToProductos() {
        return "redirect:/almacen/productos";
    }

    @GetMapping("/productos")
    public String listarProductos(@RequestParam(name = "q", required = false) String q,
                                  Model model) {

        // Ejemplo de carga de productos/búsqueda (ajusta a lo que ya tienes)
        List<ProductoDto> productos =
                (q == null || q.isBlank())
                        ? productoService.listarTodos()
                        : productoService.buscarPorTexto(q);

        List<CategoriaDto> categorias = categoriaService.listarTodos();

        // Ya no seteamos stock aquí, se maneja internamente a 0
        CrearProductoRequest nuevo = new CrearProductoRequest();
        model.addAttribute("producto", nuevo);
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        model.addAttribute("modoEdicion", false);
        model.addAttribute("query", q);
        model.addAttribute("activeMenu", "almacen");

        return "almacen/productos/listaProductos";
    }

    @PostMapping("/productos")
    public String crearProducto(@ModelAttribute("producto") CrearProductoRequest producto,
                                RedirectAttributes redirectAttributes) {
        try {
            productoService.crear(producto);
            redirectAttributes.addFlashAttribute("mensajeExito", "Producto creado correctamente");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
            redirectAttributes.addFlashAttribute("producto", producto);
        }
        return "redirect:/almacen/productos";
    }

    // ===== EDITAR (vista) =====

    @GetMapping("/productos/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        ProductoDto dto = productoService.obtenerPorId(id);
        List<ProductoDto> productos = productoService.buscarPorTexto(null);
        List<CategoriaDto> categorias = categoriaService.listarTodos();

        ActualizarProductoRequest form = new ActualizarProductoRequest();
        form.setId(dto.getId());
        form.setReferencia(dto.getReferencia());
        form.setNombre(dto.getNombre());
        form.setDescripcion(dto.getDescripcion());
        form.setIdCategoria(dto.getIdCategoria());


        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        model.addAttribute("producto", form);
        model.addAttribute("modoEdicion", true);
        model.addAttribute("query", "");

        return "almacen/productos/listaProductos";
    }

    @PostMapping("/productos/{id}/editar")
    public String actualizarProducto(@PathVariable Long id,
                                     @ModelAttribute("producto") ActualizarProductoRequest producto,
                                     RedirectAttributes redirectAttributes) {
        try {
            productoService.actualizar(id, producto);
            redirectAttributes.addFlashAttribute("mensajeExito", "Producto actualizado correctamente");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
        }
        return "redirect:/almacen/productos";
    }

    // ===== ELIMINAR (vista) =====

    @PostMapping("/productos/{id}/eliminar")
    public String eliminarProducto(@PathVariable Long id,
                                   RedirectAttributes redirectAttributes) {
        try {
            productoService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensajeExito", "Producto eliminado correctamente");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
        }
        return "redirect:/almacen/productos";
    }
}