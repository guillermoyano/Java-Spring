package com.elnoticioso.noticias.controlador;

import com.elnoticioso.noticias.MiException.Excepciones;
import com.elnoticioso.noticias.entidades.Noticia;
import com.elnoticioso.noticias.servicios.noticiaServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/noticia")
public class noticiaControlador {

    @Autowired
    private noticiaServicio noticiaServicio;
    
    
    @GetMapping("/crear")
    public String crear(){
        return "noticia_form.html";
    }
    
    @PostMapping("/creacion")
    public String creacion(@RequestParam String titulo, @RequestParam String subtitulo, @RequestParam String cuerpo, ModelMap modelo){
        
        try {
            noticiaServicio.crearNoticia(titulo, subtitulo, cuerpo);
             
             modelo.put("exito", "La noticia fue cargada correctamente");
        } catch (Excepciones ex) {
            modelo.put("error", ex.getMessage());
            
             return "noticia_form.html";
        }
     
       return "noticia_form.html";
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        List<Noticia> noticias = noticiaServicio.listarNoticias();
        modelo.addAttribute("noticias", noticias);
        return "index.html";
    }
    
    @GetMapping("/noticia_ver/{id}")
    public String ver(@PathVariable Long id, ModelMap modelo){
        
        modelo.addAttribute(noticiaServicio.getone(id));
        
       //modelo.put("noticias", noticiaServicio.getone(id));
        return "noticia_ver.html";
    } 
    
    @GetMapping("/noticia_index")
    public String direccion(){
        return "noticia_index.html";
    } 
    
    @GetMapping("/contacto")
    public String contacto(){
        return "contacto.html";
    } 
    
    
      @GetMapping("/noticia_modificar/{id}")
    public String noticiaModificar (@PathVariable Long id, ModelMap modelo){
        modelo.put("noticia",noticiaServicio.getone(id));
        
        return "noticia_modificar.html";
    }
    
    @PostMapping("/noticia_modificar/{id}")
    public String noticiaModificar(@PathVariable Long id, String titulo, String subtitulo, String cuerpo, RedirectAttributes toto){
        
        try {
            noticiaServicio.modificarNoticia(id, titulo, subtitulo, cuerpo);
            
            toto.addFlashAttribute("exito", "La noticia se ha modificado con exito");
            return "redirect:../lista";
        } catch (Excepciones ex) {
           toto.addFlashAttribute("fracaso", "La noticia no se pudo modificar");
           return "redirect:../lista";
        }
    }
    
    
     @GetMapping("/modificar")
    public String modificar(ModelMap modelo){
        List<Noticia> noticias = noticiaServicio.listarNoticias();
        modelo.addAttribute("noticias", noticias);
        return "modifica.html";
    }
    
    
       @GetMapping("/elimina")
    public String elimina (ModelMap modelo){
         List<Noticia> noticias = noticiaServicio.listarNoticias();
        modelo.addAttribute("noticias", noticias);
        return "elimina.html";
    }
      @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, ModelMap modelo) {
        modelo.put("noticia",  noticiaServicio.getone(id));

        return "elimina_noticia.html";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id,  RedirectAttributes toto) {
        try {
            noticiaServicio.eliminarNoticia(id);
            
            toto.addFlashAttribute("exito", "Muy bien");

            return "redirect:../lista";
        } catch (Excepciones ex) {
            toto.addFlashAttribute("error", "Merd");
            return "redirect:../lista";
        }

    }
    
    
    
}
