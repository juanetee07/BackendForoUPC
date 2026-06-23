package com.upc.demo.controller.noticias;

import com.upc.demo.entity.noticias.ArchivoNoticia;
import com.upc.demo.service.noticias.IArchivoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/archivo-noticias")
public class ArchivoControlador {

    @Autowired
    private IArchivoServicio archivoServicio;

    @PostMapping
    public ResponseEntity<ArchivoNoticia> agregarArchivo(
            @RequestParam Long idNoticia,
            @RequestBody ArchivoNoticia archivoNoticia) {

        ArchivoNoticia nuevoArchivo = archivoServicio.agregarArchivo(archivoNoticia, idNoticia);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoArchivo);
    }
}
