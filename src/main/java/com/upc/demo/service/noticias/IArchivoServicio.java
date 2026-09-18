package com.upc.demo.service.noticias;

import com.upc.demo.dto.request.noticias.ArchivoNoticiaRequest;
import com.upc.demo.dto.response.noticias.ArchivoNoticiaResponse;
import com.upc.demo.entity.noticias.ArchivoNoticia;

import java.util.List;

public interface IArchivoServicio {

    ArchivoNoticiaResponse agregarArchivo(ArchivoNoticiaRequest archivoNoticiaRequest, Long idNoticia);

    ArchivoNoticiaResponse modificarArchivo(Long idArchivo, ArchivoNoticiaRequest archivoNoticiaRequest);

    void eliminarArchivo(Long idArchivo);

    ArchivoNoticia descargarArchivo(Long idArchivo);

    List<ArchivoNoticiaResponse> consultarArchivosDeNoticias(Long idNoticia);
}
