package com.upc.demo.service.noticias;

import com.upc.demo.dto.request.noticias.ArchivoNoticiaRequest;
import com.upc.demo.dto.response.noticias.ArchivoNoticiaResponse;
import com.upc.demo.entity.noticias.ArchivoNoticia;
import com.upc.demo.entity.noticias.EstadoNoticia;
import com.upc.demo.entity.noticias.Noticia;
import com.upc.demo.repository.noticias.ArchivoNoticiaRepositorio;
import com.upc.demo.repository.noticias.NoticiaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchivoServicio implements IArchivoServicio{

    @Autowired
    private ArchivoNoticiaRepositorio archivoRepo;

    @Autowired
    private NoticiaRepositorio noticiaRepo;

    @Override
    public ArchivoNoticiaResponse agregarArchivo(
            ArchivoNoticiaRequest archivoNoticiaRequest,
            Long idNoticia) {

        // Verificar que la URL no sea nula ni esté vacía
        if (archivoNoticiaRequest.getUrl() == null ||
                archivoNoticiaRequest.getUrl().trim().isEmpty()) {

            throw new RuntimeException("La URL del archivo es obligatoria");
        }

        // Verificar que el tipo no sea nulo ni esté vacío
        if (archivoNoticiaRequest.getTipo() == null ||
                archivoNoticiaRequest.getTipo().trim().isEmpty()) {

            throw new RuntimeException("El tipo del archivo es obligatorio");
        }

        // Verificar que el nombre no sea nulo ni esté vacío
        if (archivoNoticiaRequest.getNombre() == null ||
                archivoNoticiaRequest.getNombre().trim().isEmpty()) {

            throw new RuntimeException("El nombre del archivo es obligatorio");
        }

        // Verificar que la noticia exista
        Noticia noticia = noticiaRepo.findById(idNoticia)
                .orElseThrow(() ->
                        new RuntimeException("La noticia no existe"));

        // Crear la entidad
        ArchivoNoticia archivoNoticia = new ArchivoNoticia();

        archivoNoticia.setNombre(archivoNoticiaRequest.getNombre().trim());
        archivoNoticia.setUrl(archivoNoticiaRequest.getUrl().trim());
        archivoNoticia.setTipo(archivoNoticiaRequest.getTipo().trim());

        // Asociar el archivo con la noticia
        archivoNoticia.setNoticia(noticia);

        // Guardar
        ArchivoNoticia archivoGuardado = archivoRepo.save(archivoNoticia);

        // Crear Response
        ArchivoNoticiaResponse response = new ArchivoNoticiaResponse();

        response.setId(archivoGuardado.getId());
        response.setNombre(archivoGuardado.getNombre());
        response.setUrl(archivoGuardado.getUrl());
        response.setTipo(archivoGuardado.getTipo());

        return response;
    }

    @Override
    public ArchivoNoticiaResponse modificarArchivo(
            Long idArchivo,
            ArchivoNoticiaRequest archivoNoticiaRequest) {

        ArchivoNoticia archivoExistente = archivoRepo.findById(idArchivo)
                .orElseThrow(() -> new RuntimeException("Archivo no encontrado"));

        if (archivoNoticiaRequest.getUrl() == null ||
                archivoNoticiaRequest.getUrl().trim().isEmpty()) {

            throw new RuntimeException("La URL del archivo es obligatoria");
        }

        if (archivoNoticiaRequest.getTipo() == null ||
                archivoNoticiaRequest.getTipo().trim().isEmpty()) {

            throw new RuntimeException("El tipo del archivo es obligatorio");
        }

        if (archivoNoticiaRequest.getNombre() == null ||
                archivoNoticiaRequest.getNombre().trim().isEmpty()) {

            throw new RuntimeException("El nombre del archivo es obligatorio");
        }

        archivoExistente.setNombre(archivoNoticiaRequest.getNombre().trim());
        archivoExistente.setUrl(archivoNoticiaRequest.getUrl().trim());
        archivoExistente.setTipo(archivoNoticiaRequest.getTipo().trim());

        ArchivoNoticia archivoGuardado = archivoRepo.save(archivoExistente);

        ArchivoNoticiaResponse response = new ArchivoNoticiaResponse();
        response.setId(archivoGuardado.getId());
        response.setNombre(archivoGuardado.getNombre());
        response.setUrl(archivoGuardado.getUrl());
        response.setTipo(archivoGuardado.getTipo());

        return response;
    }

    @Override
    public void eliminarArchivo(Long idArchivo) {

        ArchivoNoticia archivo = archivoRepo.findById(idArchivo).orElseThrow(() -> new RuntimeException("Archivo no encontrado"));
        archivoRepo.delete(archivo);

    }

    @Override
    public ArchivoNoticia descargarArchivo(Long idArchivo) {

        ArchivoNoticia archivo = archivoRepo.findById(idArchivo).orElseThrow(() -> new RuntimeException("El archivo solicitado no existe."));

        if (archivo.getNoticia().getEstado() != EstadoNoticia.PUBLICADA) {
            throw new RuntimeException("No se puede descargar el archivo porque la noticia aún no ha sido publicada.");
        }
        return archivo;
    }

    @Override
    public List<ArchivoNoticiaResponse> consultarArchivosDeNoticias(Long idNoticia) {

        noticiaRepo.findById(idNoticia)
                .orElseThrow(() -> new RuntimeException("Noticia no encontrada"));

        List<ArchivoNoticia> archivos = archivoRepo.findByNoticiaId(idNoticia);

        return archivos.stream()
                .map(archivo -> {
                    ArchivoNoticiaResponse response = new ArchivoNoticiaResponse();

                    response.setId(archivo.getId());
                    response.setNombre(archivo.getNombre());
                    response.setUrl(archivo.getUrl());
                    response.setTipo(archivo.getTipo());

                    return response;
                })
                .toList();
    }
}
