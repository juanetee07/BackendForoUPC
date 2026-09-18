package com.upc.demo.service.noticias;

import com.upc.demo.dto.request.noticias.ImagenNoticiaRequest;
import com.upc.demo.dto.response.noticias.ImagenNoticiaResponse;
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
    public ImagenNoticiaResponse agregarImagen(
            ImagenNoticiaRequest imagenNoticiaRequest,
            Long idNoticia) {

        /* Verificar que la URL no sea nula ni esté vacía */
        if (imagenNoticiaRequest.getUrl() == null ||
                imagenNoticiaRequest.getUrl().trim().isEmpty()) {

            throw new RuntimeException("La URL de la imagen es obligatoria");
        }

        /* Buscar la noticia */
        Noticia noticia = noticiaRepo.findById(idNoticia)
                .orElseThrow(() ->
                        new RuntimeException("La noticia no existe"));

        /* Crear la entidad */
        ImagenNoticia imagen = new ImagenNoticia();

        imagen.setUrl(imagenNoticiaRequest.getUrl());
        imagen.setEpigrafe(imagenNoticiaRequest.getEpigrafe());
        imagen.setNoticia(noticia);

        /* Guardar */
        ImagenNoticia imagenGuardada = imagenRepo.save(imagen);

        /* Convertir a Response */
        ImagenNoticiaResponse response = new ImagenNoticiaResponse();

        response.setId(imagenGuardada.getId());
        response.setUrl(imagenGuardada.getUrl());
        response.setEpigrafe(imagenGuardada.getEpigrafe());

        return response;
    }

    @Override
    public ImagenNoticiaResponse modificarImagen(
            Long idImagen,
            ImagenNoticiaRequest imagenNoticiaRequest) {

        ImagenNoticia imagenExistente = imagenRepo.findById(idImagen)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));

        if (imagenNoticiaRequest.getUrl() == null ||
                imagenNoticiaRequest.getUrl().trim().isEmpty()) {

            throw new RuntimeException("La URL de la imagen es obligatoria");
        }

        imagenExistente.setUrl(imagenNoticiaRequest.getUrl().trim());
        imagenExistente.setEpigrafe(imagenNoticiaRequest.getEpigrafe());

        ImagenNoticia imagenActualizada = imagenRepo.save(imagenExistente);

        ImagenNoticiaResponse response = new ImagenNoticiaResponse();

        response.setId(imagenActualizada.getId());
        response.setUrl(imagenActualizada.getUrl());
        response.setEpigrafe(imagenActualizada.getEpigrafe());

        return response;
    }

    @Override
    public void eliminarImagen(Long idImagen) {

        ImagenNoticia imagen = imagenRepo.findById(idImagen).orElseThrow(() -> new RuntimeException("Imagen no encontrada"));

        imagenRepo.delete(imagen);
    }

    @Override
    public List<ImagenNoticiaResponse> consultarImagenesPorNoticia(Long idNoticia) {

        noticiaRepo.findById(idNoticia)
                .orElseThrow(() -> new RuntimeException("Noticia no encontrada"));

        return imagenRepo.findByNoticiaId(idNoticia)
                .stream()
                .map(imagen -> {
                    ImagenNoticiaResponse response = new ImagenNoticiaResponse();

                    response.setId(imagen.getId());
                    response.setUrl(imagen.getUrl());
                    response.setEpigrafe(imagen.getEpigrafe());

                    return response;
                })
                .toList();
    }

}
