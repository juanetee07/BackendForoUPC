package com.upc.demo.service.noticias;

import com.upc.demo.dto.request.noticias.NoticiaRequest;
import com.upc.demo.dto.response.noticias.NoticiaResponse;
import com.upc.demo.entity.noticias.EstadoNoticia;
import com.upc.demo.entity.noticias.Noticia;

import java.time.LocalDateTime;
import java.util.List;

public interface INoticiaServicio {

    List<NoticiaResponse> listarNoticias();

    NoticiaResponse crearNoticia(NoticiaRequest noticiaRequest, Long usuarioId);

    NoticiaResponse editarNoticia(Long idNoticia, NoticiaRequest noticiaActualizada, Long idAutor);

    Noticia publicarNoticia(Long idNoticia, Long idAutor);

    Noticia cambiarEstado(Long idNoticia, EstadoNoticia nuevoEstado, Long idAutor);

    NoticiaResponse consultarNoticia(Long idNoticia);

    List<Noticia> buscarNoticiaPorTitulo(String titulo);

    List<Noticia> buscarNoticiaPorCategoria(Long categoriaId);

    List<Noticia> buscarNoticiaPorFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    void eliminarNoticia(Long idNoticia);
}
