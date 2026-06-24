package com.upc.demo.controller.noticias;

import com.upc.demo.entity.noticias.ImagenNoticia;
import com.upc.demo.service.noticias.IImagenServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/imagen-noticias")
public class ImagenControlador {

    @Autowired
    private IImagenServicio imagenServicio;

    @PostMapping
    public ResponseEntity<ImagenNoticia> modificarImagen(
            @RequestParam Long idNoticia,
            @RequestBody ImagenNoticia imagenNoticia) {

        ImagenNoticia imagenActualizada = imagenServicio.modificarImagen(idNoticia, imagenNoticia);

        return ResponseEntity.status(HttpStatus.CREATED).body(imagenActualizada);
    }

    @GetMapping("/imagen/{idNoticia}")
    public ResponseEntity<List<ImagenNoticia>> consultarImagenesPorNoticia(
            @PathVariable Long idNoticia){

        return ResponseEntity.ok(imagenServicio.consultarImagenesPorNoticia(idNoticia)

        );
    }
}
