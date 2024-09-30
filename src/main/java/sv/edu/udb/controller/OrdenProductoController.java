package sv.edu.udb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.model.OrdenProducto;
import sv.edu.udb.model.Producto;
import sv.edu.udb.model.Orden;
import sv.edu.udb.repository.OrdenProductoRepository;
import sv.edu.udb.repository.ProductoRepository;
import sv.edu.udb.repository.OrdenRepository;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/orden-producto")
public class OrdenProductoController {

    @Autowired
    private OrdenProductoRepository ordenProductoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private OrdenRepository ordenRepository;

    // Listar todos los productos en una orden
    @GetMapping
    public String listarOrdenProductos(Model model) {
        List<OrdenProducto> ordenProductos = ordenProductoRepository.findAll();
        model.addAttribute("ordenProductos", ordenProductos);
        return "orden_producto/lista"; // Plantilla Thymeleaf
    }

    // Mostrar el formulario para agregar un producto a una orden
    @GetMapping("/nuevo")
    public String mostrarFormularioDeOrdenProducto(Model model) {
        model.addAttribute("ordenProducto", new OrdenProducto());
        model.addAttribute("productos", productoRepository.findAll());
        model.addAttribute("ordenes", ordenRepository.findAll());
        return "orden_producto/formulario"; // Plantilla Thymeleaf
    }

    // Guardar un nuevo producto en la orden
    @PostMapping("/guardar")
    public String guardarOrdenProducto(@Valid OrdenProducto ordenProducto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("productos", productoRepository.findAll());
            model.addAttribute("ordenes", ordenRepository.findAll());
            return "orden_producto/formulario";
        }
        ordenProductoRepository.save(ordenProducto);
        return "redirect:/orden-producto";
    }

    // Editar un producto en la orden
    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable("id") Long id, Model model) {
        OrdenProducto ordenProducto = ordenProductoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("ordenProducto", ordenProducto);
        model.addAttribute("productos", productoRepository.findAll());
        model.addAttribute("ordenes", ordenRepository.findAll());
        return "orden_producto/formulario";
    }

    // Actualizar un producto en la orden
    /*@PostMapping("/actualizar/{id}")
    public String actualizarOrdenProducto(@PathVariable("id") Long id, @Valid OrdenProducto ordenProducto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            ordenProducto.setOrden(id);
            return "orden_producto/formulario";
        }
        ordenProductoRepository.save(ordenProducto);
        return "redirect:/orden-producto";
    }*/

    /*// Eliminar un producto de la orden
    @GetMapping("/eliminar/{id}")
    public String eliminarOrdenProducto(@PathVariable("id") Long id, Model model) {
        OrdenProducto ordenProducto = ordenProductoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        ordenProductoRepository.delete(ordenProducto);
        return "redirect:/orden-producto";
    }*/
}

