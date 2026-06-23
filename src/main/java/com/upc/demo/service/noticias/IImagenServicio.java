package com.upc.demo.service.noticias;

import com.upc.demo.entity.noticias.ImagenNoticia;

public interface IImagenServicio {

    ImagenNoticia agregarImagen(ImagenNoticia imagen, Long idNoticia);
}
