package com.elnoticioso.noticias.controlador;

import com.elnoticioso.noticias.entidades.Noticia;
import com.elnoticioso.noticias.servicios.noticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private noticiaServicio noticiaServicio;

    @GetMapping("/")
    public String index(ModelMap modelo) {
        List<Noticia> noticias = noticiaServicio.listarNoticias();
        modelo.addAttribute("noticias", noticias);
        return "index.html";
    }

    @GetMapping("registrar")
    public String registrar() {
        return "registro.html";
    }

    @GetMapping("login")
    public String login() {
        return "login.html";
    }
}
