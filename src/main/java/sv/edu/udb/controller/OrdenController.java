package sv.edu.udb.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sv.edu.udb.model.Orden;
import sv.edu.udb.repository.OrdenRepository;
import sv.edu.udb.repository.ProductoRepository;
import sv.edu.udb.repository.UsuarioRepository;

@Controller
public class OrdenController {

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // Mostrar formulario para crear una orden
    @GetMapping("/orden/crear")
    public String mostrarFormularioOrden(Model model) {
        model.addAttribute("orden", new Orden());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("productos", productoRepository.findAll());
        return "orden_form";
    }

    // Manejar el envío del formulario de creación de orden
    @PostMapping("/orden/crear")
    public String registrarOrden(@Valid @ModelAttribute("orden") Orden orden, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("usuarios", usuarioRepository.findAll());
            model.addAttribute("productos", productoRepository.findAll());
            return "orden_form";
        }

        ordenRepository.save(orden);
        return "redirect:/orden/lista?exito";
    }

    // Mostrar lista de órdenes
    @GetMapping("/orden/lista")
    public String listarOrdenes(Model model) {
        model.addAttribute("ordenes", ordenRepository.findAll());
        return "orden_lista";
    }
}

