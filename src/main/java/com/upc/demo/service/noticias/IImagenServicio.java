package com.upc.demo.service.noticias;

import com.upc.demo.entity.noticias.ImagenNoticia;

import java.util.List;

public interface IImagenServicio {

    ImagenNoticia agregarImagen(ImagenNoticia imagen, Long idNoticia);

    ImagenNoticia modificarImagen(Long idImagen, ImagenNoticia imagenActualizada);

    List<ImagenNoticia> consultarImagenesPorNoticia(Long idNoticia);
}
