package com.upc.demo.service.noticias;

import com.upc.demo.entity.noticias.ArchivoNoticia;

public interface IArchivoServicio {

    ArchivoNoticia agregarArchivo(ArchivoNoticia archivoNoticia, Long idNoticia);
}
