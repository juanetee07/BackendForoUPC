package com.upc.demo.service.noticias;

import com.upc.demo.entity.noticias.Noticia;

public interface INoticiaServicio {

    Noticia crearNoticia(Noticia noticia, Long idAutor);
}
