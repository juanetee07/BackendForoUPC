package com.upc.demo.service.noticias;

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
    public ArchivoNoticia agregarArchivo(ArchivoNoticia archivoNoticia, Long idNoticia) {

        /*Verificar que la url no sea un valor nulo ni que este vacia*/
        if (archivoNoticia.getUrl() == null || archivoNoticia.getUrl().trim().isEmpty()) {
            throw new RuntimeException("La URL del archivo es obligatorio");
        }

        if (archivoNoticia.getTipo() == null || archivoNoticia.getTipo().trim().isEmpty()) {
            throw new RuntimeException("El tipo del archivo es obligatorio");
        }

        if (archivoNoticia.getNombre() == null || archivoNoticia.getNombre().trim().isEmpty()) {
            throw new RuntimeException("El nombre del archivo es obligatorio");
        }
        /*Un archivo se debe de asociar con una noticia existente*/
        Noticia noticia = noticiaRepo.findById(idNoticia).orElseThrow(() -> new RuntimeException("La noticia no existe"));

        archivoNoticia.setNoticia(noticia);

        return archivoRepo.save(archivoNoticia);
    }

    @Override
    public ArchivoNoticia modificarArchivo(Long idArchivo, ArchivoNoticia archivoActualizado) {
        ArchivoNoticia archivoExistente = archivoRepo.findById(idArchivo).orElseThrow(() -> new RuntimeException("Archivo no encontrado"));

        if (archivoActualizado.getUrl() == null || archivoActualizado.getUrl().trim().isEmpty()) {
            throw new RuntimeException("La URL del archivo es obligatorio");
        }

        if (archivoActualizado.getTipo() == null || archivoActualizado.getTipo().trim().isEmpty()) {
            throw new RuntimeException("El tipo del archivo es obligatorio");
        }

        if (archivoActualizado.getNombre() == null || archivoActualizado.getNombre().trim().isEmpty()) {
            throw new RuntimeException("El nombre del archivo es obligatorio");
        }

        archivoExistente.setUrl(archivoActualizado.getUrl().trim());

        archivoExistente.setTipo(archivoActualizado.getTipo().trim());

        archivoExistente.setNombre(archivoActualizado.getNombre().trim());

        return archivoRepo.save(archivoExistente);
    }

    @Override
    public ArchivoNoticia eliminarArchivo(Long idArchivo) {

        ArchivoNoticia archivo = archivoRepo.findById(idArchivo).orElseThrow(() -> new RuntimeException("Archivo no encontrado"));
        archivoRepo.delete(archivo);

        return archivo;
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
    public List<ArchivoNoticia> consultarArchivosDeNoticias(Long idNoticia) {
        noticiaRepo.findById(idNoticia).orElseThrow(() -> new RuntimeException("Noticia no encontrada"));
        return archivoRepo.findByNoticiaId(idNoticia);
    }
}
