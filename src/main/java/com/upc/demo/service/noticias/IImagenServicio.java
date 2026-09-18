package com.upc.demo.service.noticias;

import com.upc.demo.dto.request.noticias.ImagenNoticiaRequest;
import com.upc.demo.dto.response.noticias.ImagenNoticiaResponse;
import com.upc.demo.entity.noticias.ImagenNoticia;

import java.util.List;

public interface IImagenServicio {

    ImagenNoticiaResponse agregarImagen(ImagenNoticiaRequest imagenNoticiaRequest, Long idNoticia);

    ImagenNoticiaResponse modificarImagen(Long idImagen, ImagenNoticiaRequest imagenNoticiaRequest);

    void eliminarImagen(Long idImagen);

    List<ImagenNoticiaResponse> consultarImagenesPorNoticia(Long idNoticia);
}
