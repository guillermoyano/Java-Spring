package com.elnoticioso.noticias.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String titulo;
    private String subtitulo;
    private String cuerpo;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @ManyToOne
    private Periodistas creador;

    public Noticia() {
    }

    public Noticia(Long id, String titulo, String subtitulo, String cuerpo, Date fecha, Periodistas creador) {
        this.id = id;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.cuerpo = cuerpo;
        this.fecha = fecha;
        this.creador = creador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Periodistas getCreador() {
        return creador;
    }

    public void setCreador(Periodistas creador) {
        this.creador = creador;
    }
    

    
    
}
