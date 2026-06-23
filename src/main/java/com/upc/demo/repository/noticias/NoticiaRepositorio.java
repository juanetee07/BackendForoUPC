package com.upc.demo.repository.noticias;

import com.upc.demo.entity.noticias.EstadoNoticia;
import com.upc.demo.entity.noticias.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, Long>{

    /*Buscar noticias por titulo*/
    List<Noticia> findByTitulo(String titulo);

    /*Buscar una noticia cuyo titulo contenga una cadena (sin importar mayúsculas/minúsculas)*/
    List<Noticia> findByTituloContainingIgnoreCase(String titulo);

    /*Buscar todas las noticias de un autor*/
    List<Noticia> findByAutorIdUsuario(Long idUsuario);

    /*Buscar todas las noticias de una categoria*/
    List<Noticia> findByCategoriaNoticiaId(Long categoriaId);

    /*Buscar todas las noticias de una categoria*/
    List<Noticia> findByEstado(EstadoNoticia estado);

    /*Listar todas las noticias publicadas en una determianda fecha*/
    List<Noticia> findByEstadoOrderByFechaPublicacionDesc(
            EstadoNoticia estado);

    /*Buscar una noticia de acuerdo a la categoria y el estado*/
    List<Noticia> findByCategoriaNoticiaIdAndEstado(
            Long categoriaId,
            EstadoNoticia estado);

    List<Noticia> findByFechaPublicacionBetween(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin);

}
