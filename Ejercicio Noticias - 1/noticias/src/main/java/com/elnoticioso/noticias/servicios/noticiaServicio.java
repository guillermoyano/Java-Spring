package com.elnoticioso.noticias.servicios;

import com.elnoticioso.noticias.MiException.Excepciones;
import com.elnoticioso.noticias.entidades.Noticia;
import com.elnoticioso.noticias.repositorio.noticiaRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class noticiaServicio {

    @Autowired
    private noticiaRepositorio noticiaRepositorio;
    
    @Transactional
    public void crearNoticia(String titulo, String subtitulo, String cuerpo) throws Excepciones{
        
        validar(titulo, subtitulo, cuerpo);
        
        Noticia noticia = new Noticia();
        
        noticia.setTitulo(titulo);
        noticia.setSubtitulo(subtitulo);
        noticia.setCuerpo(cuerpo);
        
        noticia.setFecha(new Date());
        
        noticiaRepositorio.save(noticia);
    }
    
    
    public List<Noticia> listarNoticias(){
        
        List<Noticia> noticias = new ArrayList();
        
        noticias = noticiaRepositorio.findAllOrderByidDesc();
        
        return noticias;
        
    }
    
    @Transactional
    public void modificarNoticia(Long id, String titulo, String subtitulo, String cuerpo) throws Excepciones{
        
        validar(titulo, subtitulo, cuerpo);
        
        Optional<Noticia> respuesta = noticiaRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            Noticia noticia = respuesta.get();
            noticia.setTitulo(titulo);
            noticia.setSubtitulo(subtitulo);
            noticia.setCuerpo(cuerpo);
            
            noticiaRepositorio.save(noticia);
        }
    }
    
    @Transactional
    public void eliminarNoticia(Long id) throws Excepciones{
               
        Optional<Noticia> respuesta = noticiaRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            
            Noticia noticia = respuesta.get();
            
            noticiaRepositorio.delete(noticia);
        }
    }
    
    private void validar(String titulo, String subtitulo, String cuerpo) throws Excepciones{
             
        if(titulo.isEmpty() || titulo == null){
            throw new Excepciones("El titulo de la noticia no puede ser nulo o estar vacío");
        }
        
         if(subtitulo.isEmpty() || subtitulo == null){
            throw new Excepciones("El subtitulo de la noticia no puede ser nulo o estar vacío");
        }
         
          if(cuerpo.isEmpty() || cuerpo == null){
            throw new Excepciones("El cuerpo de la noticia no puede ser nulo o estar vacío");
        }
        
    }
    
    public Noticia getone(Long id){
        return noticiaRepositorio.getOne(id);
    }
    
    
   
    
    
}
