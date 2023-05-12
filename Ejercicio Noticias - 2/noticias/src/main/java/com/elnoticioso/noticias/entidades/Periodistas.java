package com.elnoticioso.noticias.entidades;

import com.elnoticioso.noticias.enumeraciones.Rol;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Periodistas extends Usuario{
    
    
    private ArrayList<Noticia> misNoticias;
    private Integer sueldoMensual;

    public Periodistas() {
    }

    public Periodistas(ArrayList<Noticia> misNoticias, Integer sueldoMensual, String nombreUsusario, String password, Date fechaDeAlta, Rol rol, Boolean activo) {
        super(nombreUsusario, password, fechaDeAlta, rol, activo);
        this.misNoticias = misNoticias;
        this.sueldoMensual = sueldoMensual;
    }

    public ArrayList<Noticia> getMisNoticias() {
        return misNoticias;
    }

    public void setMisNoticias(ArrayList<Noticia> misNoticias) {
        this.misNoticias = misNoticias;
    }

    public Integer getSueldoMensual() {
        return sueldoMensual;
    }

    public void setSueldoMensual(Integer sueldoMensual) {
        this.sueldoMensual = sueldoMensual;
    }

   
    
    

}
