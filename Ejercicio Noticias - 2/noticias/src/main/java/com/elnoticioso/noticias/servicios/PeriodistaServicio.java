package com.elnoticioso.noticias.servicios;

import com.elnoticioso.noticias.MiException.Excepciones;
import com.elnoticioso.noticias.entidades.Periodistas;
import com.elnoticioso.noticias.entidades.Usuario;
import com.elnoticioso.noticias.enumeraciones.Rol;
import com.elnoticioso.noticias.repositorio.PeriodistaRepositorio;
import com.elnoticioso.noticias.repositorio.UsuarioRepositorio;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeriodistaServicio {

     @Autowired
    private PeriodistaRepositorio periodistaRepositorio;
    
    @Transactional
    public void registrar(String nombreUsusario, String password, String password2) throws Excepciones{
        
        validar(nombreUsusario, password, password2);
        
        Periodistas periodistas = new Periodistas();
        
        periodistas.setNombreUsusario(nombreUsusario);
        periodistas.setPassword(password);
        
        periodistas.setFechaDeAlta(new Date() );
        periodistas.setActivo(Boolean.TRUE);
        
        periodistas.setRol(Rol.USER);
        
        periodistaRepositorio.save(periodistas);
    }
    
    
    public void validar(String nombreUsusario, String password, String password2) throws Excepciones{
        
        if (nombreUsusario.isEmpty() || nombreUsusario == null) {
            throw new Excepciones("El nombre de usuario no puede ser nulo o estar vacío");
        }
         if (password.isEmpty() || password == null || password.length() <=5) {
            throw new Excepciones("La contraseña no puede ser nula o tener menos de 5 carácteres");
        }
         if(!password.equals(password2)){
             throw new Excepciones("Las contraseñas ingresadas no son iguales");
         }
        
    }
}
