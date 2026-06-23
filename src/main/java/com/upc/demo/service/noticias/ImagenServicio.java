package com.upc.demo.service.noticias;

import com.upc.demo.entity.noticias.ImagenNoticia;
import com.upc.demo.entity.noticias.Noticia;
import com.upc.demo.repository.noticias.ImagenNoticiaRepositorio;
import com.upc.demo.repository.noticias.NoticiaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ImagenServicio implements IImagenServicio {

    @Autowired
    private ImagenNoticiaRepositorio imagenRepo;

    @Autowired
    private NoticiaRepositorio noticiaRepo;


    @Override
    public ImagenNoticia agregarImagen(ImagenNoticia imagen, Long idNoticia) {

        /*Verificar que la url no sea un valor nulo ni que este vacia*/
        if (imagen.getUrl() == null || imagen.getUrl().trim().isEmpty()) {
            throw new RuntimeException("La URL de la imagen es obligatoria");
        }

        /*Una imagen se debe de asociar con una noticia existente*/
        Noticia noticia = noticiaRepo.findById(idNoticia).orElseThrow(() -> new RuntimeException("La noticia no existe"));

        imagen.setNoticia(noticia);

        return imagenRepo.save(imagen);
    }
}
