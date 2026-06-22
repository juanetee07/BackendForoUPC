package com.upc.demo.service.noticias;

import com.upc.demo.entity.noticias.EstadoNoticia;
import com.upc.demo.entity.noticias.Noticia;

public interface INoticiaServicio {

    Noticia crearNoticia(Noticia noticia, Long idAutor);

    Noticia editarNoticia(Long idNoticia, Noticia noticiaActualizada, Long idAutor);

    Noticia publicarNoticia(Long idNoticia, Long idAutor);

    Noticia cambiarEstado(Long idNoticia, EstadoNoticia nuevoEstado, Long idAutor);

    Noticia consultarNoticia(Long idNoticia);
}
