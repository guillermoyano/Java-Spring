package com.elnoticioso.noticias.repositorio;

import com.elnoticioso.noticias.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository <Usuario, String> {
    
    @Query("SELECT u FROM Ususario u WHERE u.nombreUsuario = :nombreUsuario")
    public Usuario buscarPornombreUsusario (@Param("nombreUsusario") String nombreUsuario);

}
