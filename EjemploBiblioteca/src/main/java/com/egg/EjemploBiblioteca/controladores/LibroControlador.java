package com.egg.EjemploBiblioteca.controladores;

import com.egg.EjemploBiblioteca.entidades.Autor;
import com.egg.EjemploBiblioteca.entidades.Editorial;
import com.egg.EjemploBiblioteca.entidades.Libro;
import com.egg.EjemploBiblioteca.excepciones.MiException;
import com.egg.EjemploBiblioteca.servicios.AutorServicio;
import com.egg.EjemploBiblioteca.servicios.EditorialServicio;
import com.egg.EjemploBiblioteca.servicios.LibroServicio;
import java.util.List;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/libro")
@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
public class LibroControlador {

    //<>
    @Autowired
    private LibroServicio libroServicio;
    @Autowired
    private AutorServicio autorServicio;
    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/registrar")
    public String registrar(ModelMap modelo) {
        List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);

        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long isbn, @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Integer ejemplares, @RequestParam String idAutor,
            @RequestParam String idEditorial, ModelMap modelo) {

        try {
            libroServicio.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
            modelo.put("exito", "El libro fue cargado con exito");
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            java.util.logging.Logger.getLogger(LibroControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "libro_form.html";
        }
        return "libro_form.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {
        List<Libro> libros = libroServicio.listarLibros();
        modelo.put("libros", libros);

        return "libro_list.html";
    }

    @GetMapping("/eliminar/{isbn}")
    public String eliminar(@PathVariable Long isbn, ModelMap modelo) {
        modelo.put("libro", libroServicio.getone(isbn));

        return "libro_eliminar.html";
    }

    @PostMapping("/eliminar/{isbn}")
    public String eliminar(@PathVariable Long isbn, RedirectAttributes toto) {
        try {
            libroServicio.eliminarLibro(isbn);
            toto.addFlashAttribute("exito", "Muy bien");

            return "redirect:../lista";
        } catch (MiException ex) {
            toto.addFlashAttribute("error", "Merd");
            return "redirect:../lista";
        }

    }

    @GetMapping("/modificar/{isbn}")
    public String modificar(@PathVariable Long isbn, ModelMap modelo) {

        modelo.put("libro", libroServicio.getone(isbn));

        List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);

        return "libro_modificar.html";
    }

    @PostMapping("/modificar/{isbn}")
    public String modificar(@RequestParam(required = false) Long isbn, @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Integer ejemplares, @RequestParam String idAutor,
            @RequestParam String idEditorial, RedirectAttributes toto) {

        try {
            List<Autor> autores = autorServicio.listarAutores();
            List<Editorial> editoriales = editorialServicio.listarEditoriales();

            toto.addAttribute("autores", autores);
            toto.addAttribute("editoriales", editoriales);
            toto.addFlashAttribute("exito", "El libro se modifico correctamente");

            libroServicio.modificarLibro(isbn, titulo, idAutor, idEditorial, ejemplares);
            return "redirect:../lista";

        } catch (MiException ex) {

            toto.addFlashAttribute("fracaso", "El libro no puede ser modificado correctamente");
            return "redirect:../modificar";

        }

    }

}
