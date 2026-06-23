package com.upc.demo.controller.noticias;

import com.upc.demo.entity.noticias.Noticia;
import com.upc.demo.service.noticias.INoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/noticias")
public class NoticiaControlador {

    @Autowired
    private INoticiaServicio noticiaServicio;

    @GetMapping("/todos")
    public ResponseEntity<List<Noticia>> listarNoticias() {

        List<Noticia> noticias = noticiaServicio.listarNoticias();

        return ResponseEntity.ok(noticias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Noticia> consultarNoticia(
            @PathVariable Long id){

        return ResponseEntity.ok(noticiaServicio.consultarNoticia(id)
        );
    }

    @PostMapping
    public ResponseEntity<Noticia> crearNoticia(
            @RequestBody Noticia noticia,
            @RequestParam Long idAutor){

        Noticia nuevaNoticia = noticiaServicio.crearNoticia(noticia, idAutor);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaNoticia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Noticia> editarNoticia(
            @PathVariable Long id,
            @RequestBody Noticia noticia,
            @RequestParam Long idAutor){

        Noticia noticiaActualizada = noticiaServicio.editarNoticia(id, noticia, idAutor);

        return ResponseEntity.ok(noticiaActualizada);
    }

}
