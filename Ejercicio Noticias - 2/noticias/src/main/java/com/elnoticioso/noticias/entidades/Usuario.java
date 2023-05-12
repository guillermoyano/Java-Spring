package com.elnoticioso.noticias.entidades;

import com.elnoticioso.noticias.enumeraciones.Rol;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String nombreUsusario;
    
    private String password;
    
    private Date fechaDeAlta;
    
    @Enumerated(EnumType.STRING)
    private Rol rol;
    
    private Boolean activo;

    public Usuario() {
    }

    public Usuario(String nombreUsusario, String password, Date fechaDeAlta, Rol rol, Boolean activo) {
        this.nombreUsusario = nombreUsusario;
        this.password = password;
        this.fechaDeAlta = fechaDeAlta;
        this.rol = rol;
        this.activo = activo;
    }

    public String getNombreUsusario() {
        return nombreUsusario;
    }

    public void setNombreUsusario(String nombreUsusario) {
        this.nombreUsusario = nombreUsusario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaDeAlta() {
        return fechaDeAlta;
    }

    public void setFechaDeAlta(Date fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    
    
}
