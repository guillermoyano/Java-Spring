package com.elnoticioso.noticias.repositorio;

import com.elnoticioso.noticias.entidades.Noticia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface noticiaRepositorio extends JpaRepository <Noticia, Long>{

    @Query("SELECT n FROM Noticia n WHERE n.titulo = :titulo")
    public Noticia buscarPorTitulo(@Param("titulo") String titulo);
    
    @Query(value = "select * from noticia order by id desc",nativeQuery = true)
       List<Noticia>  findAllOrderByidDesc();
    
    
    
    
}
