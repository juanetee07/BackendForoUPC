package com.upc.demo.service.noticias;

import com.upc.demo.entity.noticias.EstadoNoticia;
import com.upc.demo.entity.noticias.Noticia;

import java.time.LocalDateTime;
import java.util.List;

public interface INoticiaServicio {

    List<Noticia> listarNoticias();

    Noticia crearNoticia(Noticia noticia, Long idAutor);

    Noticia editarNoticia(Long idNoticia, Noticia noticiaActualizada, Long idAutor);

    Noticia publicarNoticia(Long idNoticia, Long idAutor);

    Noticia cambiarEstado(Long idNoticia, EstadoNoticia nuevoEstado, Long idAutor);

    Noticia consultarNoticia(Long idNoticia);

    List<Noticia> buscarNoticiaPorTitulo(String titulo);

    List<Noticia> buscarNoticiaPorCategoria(Long categoriaId);

    List<Noticia> buscarNoticiaPorFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    Noticia eliminarNoticia(Long idNoticia);
}
