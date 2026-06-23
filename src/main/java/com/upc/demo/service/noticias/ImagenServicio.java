package com.upc.demo.service.noticias;

import com.upc.demo.entity.noticias.ImagenNoticia;
import com.upc.demo.entity.noticias.Noticia;
import com.upc.demo.repository.noticias.ImagenNoticiaRepositorio;
import com.upc.demo.repository.noticias.NoticiaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

    @Override
    public ImagenNoticia modificarImagen(Long idImagen, ImagenNoticia imagenActualizada) {

        ImagenNoticia imagenExistente = imagenRepo.findById(idImagen).orElseThrow(() -> new RuntimeException("Imagen no encontrada"));

        if (imagenActualizada.getUrl() == null || imagenActualizada.getUrl().trim().isEmpty()) {
            throw new RuntimeException("La URL de la imagen es obligatoria");
        }

        imagenExistente.setUrl(imagenActualizada.getUrl().trim());

        imagenExistente.setEpigrafe(imagenActualizada.getEpigrafe());

        return imagenRepo.save(imagenExistente);
    }

    @Override
    public ImagenNoticia eliminarImagen(Long idImagen) {
        ImagenNoticia imagen = imagenRepo.findById(idImagen).orElseThrow(() -> new RuntimeException("Imagen no encontrada"));

        imagenRepo.delete(imagen);
        return imagen;
    }

    @Override
    public List<ImagenNoticia> consultarImagenesPorNoticia(Long idNoticia) {

        noticiaRepo.findById(idNoticia).orElseThrow(() -> new RuntimeException("Noticia no encontrada"));

        return imagenRepo.findByNoticiaId(idNoticia);
    }

}
