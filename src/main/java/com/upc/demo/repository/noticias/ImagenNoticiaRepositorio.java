package com.upc.demo.repository.noticias;

import com.upc.demo.entity.noticias.ImagenNoticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagenNoticiaRepositorio extends JpaRepository<ImagenNoticia, Long> {
    /*Buscar todas las imagenes de noticias por epigrafe*/
    List<ImagenNoticia> findByEpigrafeContainingIgnoreCase(String epigrafe);

    /*Buscar todas las imagenes de noticias por url*/
    List<ImagenNoticia> findByUrlContainingIgnoreCase(String url);

    /*Buscar todas las imágenes asociadas a una noticia*/
    List<ImagenNoticia> findByNoticiaId(Long noticiaId);

}
