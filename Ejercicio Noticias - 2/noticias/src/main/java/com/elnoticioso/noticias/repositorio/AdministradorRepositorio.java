package com.elnoticioso.noticias.repositorio;

import com.elnoticioso.noticias.entidades.Administrador;
import com.elnoticioso.noticias.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


    @Repository
public interface AdministradorRepositorio extends JpaRepository <Administrador, String> {
    
    @Query("SELECT a FROM Ususario a WHERE a.nombreUsuario = :nombreUsuario")
    public Usuario buscarPornombreUsusario (@Param("nombreUsusario") String nombreUsuario);

}

