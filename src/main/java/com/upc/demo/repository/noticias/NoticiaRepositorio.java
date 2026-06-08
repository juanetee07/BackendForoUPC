package com.upc.demo.repository.noticias;

import com.upc.demo.entity.noticias.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, Long>{

    /*Buscar noticias por titulo*/
    List<Noticia> findByTitulo(String titulo);

    /*Buscar noticias cuyo titulo contenga una cadena (sin importar mayúsculas/minúsculas)*/
    List<Noticia> findByTituloContainingIgnoreCase(String titulo);

    /*Buscar todas las noticias de un autor*/
    List<Noticia> findByAutorId(Long autorId);

    /*Buscar todas las noticias de una categoria*/
    List<Noticia> findByCategoriaNoticiaId(Long categoriaId);

}
