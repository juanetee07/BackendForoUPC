package com.upc.demo.repository.noticias;

import com.upc.demo.entity.noticias.ArchivoNoticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchivoNoticiaRepositorio extends JpaRepository<ArchivoNoticia, Long> {

    /*Buscar archivos de noticias por nombre*/
    List<ArchivoNoticia> findByNombre(String nombre);

    /*Buscar archivos de noticias cuyo tipo contenga una cadena (sin importar mayúsculas/minúsculas)*/
    List<ArchivoNoticia> findByTipoContainingIgnoreCase(String tipo);

    /* Buscar todos los archivos asociados a una noticia */
    List<ArchivoNoticia> findByNoticiaId(Long noticiaId);

}
