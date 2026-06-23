package com.upc.demo.service.noticias;

import com.upc.demo.entity.noticias.ArchivoNoticia;

public interface IArchivoServicio {

    ArchivoNoticia agregarArchivo(ArchivoNoticia archivoNoticia, Long idNoticia);

    ArchivoNoticia modificarArchivo(Long idArchivo, ArchivoNoticia archivoActualizado);

    ArchivoNoticia eliminarArchivo(Long idArchivo);

    ArchivoNoticia descargarArchivo(Long idArchivo);
}
