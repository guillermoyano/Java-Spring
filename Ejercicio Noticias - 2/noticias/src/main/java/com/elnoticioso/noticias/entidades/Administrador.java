package com.elnoticioso.noticias.entidades;

import com.elnoticioso.noticias.enumeraciones.Rol;
import java.util.Date;
import javax.persistence.Entity;


@Entity
public class Administrador extends Usuario{
    
    
    private String apellido;

    public Administrador() {
    }

    public Administrador(String apellido, String nombreUsusario, String password, Date fechaDeAlta, Rol rol, Boolean activo) {
        super(nombreUsusario, password, fechaDeAlta, rol, activo);
        this.apellido = apellido;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    

    
}
