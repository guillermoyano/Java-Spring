package com.elnoticioso.noticias.repositorio;

import com.elnoticioso.noticias.entidades.Periodistas;
import com.elnoticioso.noticias.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


    
    @Repository
public interface PeriodistaRepositorio extends JpaRepository <Periodistas, String> {
    
   @Query("SELECT p FROM Ususario p WHERE p.nombreUsuario = :nombreUsuario")
    public Usuario buscarPornombreUsusario (@Param("nombreUsusario") String nombreUsuario);

}

