package sv.edu.udb.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sv.edu.udb.model.Producto;
import sv.edu.udb.repository.ProductoRepository;

@Controller
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    // Mostrar formulario para registrar un nuevo producto
    @GetMapping("/producto/crear")
    public String mostrarFormularioProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "producto_form";
    }

    // Manejar el envío del formulario de creación de producto
    @PostMapping("/producto/crear")
    public String registrarProducto(@Valid @ModelAttribute("producto") Producto producto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "producto_form";
        }

        productoRepository.save(producto);
        return "redirect:/producto/lista?exito";
    }

    // Mostrar lista de productos
    @GetMapping("/producto/lista")
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoRepository.findAll());
        return "producto_lista";
    }
}

