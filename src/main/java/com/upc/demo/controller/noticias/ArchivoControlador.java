package com.upc.demo.controller.noticias;

import com.upc.demo.entity.noticias.ArchivoNoticia;
import com.upc.demo.service.noticias.IArchivoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/noticia/{idNoticia}")
    public ResponseEntity<List<ArchivoNoticia>> consultarArchivosDeNoticias(
            @PathVariable Long idNoticia){

        return ResponseEntity.ok(archivoServicio.consultarArchivosDeNoticias(idNoticia)
                
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArchivoNoticia> modificarArchivo(
            @PathVariable Long id,
            @RequestBody ArchivoNoticia archivoActualizado){

        ArchivoNoticia archivoModificado = archivoServicio.modificarArchivo(id, archivoActualizado);

        return ResponseEntity.ok(archivoModificado);
    }


}
