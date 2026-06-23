package com.upc.demo.controller.noticias;

import com.upc.demo.entity.noticias.Noticia;
import com.upc.demo.service.noticias.INoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
