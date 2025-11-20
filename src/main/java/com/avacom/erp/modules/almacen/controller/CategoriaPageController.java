package com.avacom.erp.modules.almacen.controller;

import com.avacom.erp.modules.almacen.dto.CategoriaDto;
import com.avacom.erp.modules.almacen.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/almacen/categorias")
@RequiredArgsConstructor
public class CategoriaPageController {

    private final CategoriaService categoriaService;

    @GetMapping
    public String listarCategorias(
            @RequestParam(name = "q", required = false) String query,
            Model model
    ) {
        List<CategoriaDto> categorias = categoriaService.listarTodos();

        // filtro simple en memoria por nombre / descripcion
        if (query != null && !query.isBlank()) {
            String q = query.toLowerCase();
            categorias = categorias.stream()
                    .filter(c ->
                            (c.getNombre() != null && c.getNombre().toLowerCase().contains(q)) ||
                                    (c.getDescripcion() != null && c.getDescripcion().toLowerCase().contains(q)))
                    .toList();
        }

        if (!model.containsAttribute("categoria")) {
            model.addAttribute("categoria", new CategoriaDto());
        }

        model.addAttribute("categorias", categorias);
        model.addAttribute("query", query == null ? "" : query);

        return "almacen/categorias/listaCategorias";
    }

    @PostMapping
    public String crearCategoria(@ModelAttribute("categoria") CategoriaDto categoria,
                                 RedirectAttributes redirectAttributes) {
        try {
            categoriaService.crear(categoria);
            redirectAttributes.addFlashAttribute("mensajeExito", "Categoría creada correctamente");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
            redirectAttributes.addFlashAttribute("categoria", categoria);
        }
        return "redirect:/almacen/categorias";
    }
}