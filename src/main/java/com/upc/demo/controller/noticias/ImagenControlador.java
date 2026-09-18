package com.upc.demo.controller.noticias;

import com.upc.demo.dto.request.noticias.ImagenNoticiaRequest;
import com.upc.demo.dto.response.noticias.ImagenNoticiaResponse;
import com.upc.demo.entity.noticias.ImagenNoticia;
import com.upc.demo.service.noticias.IImagenServicio;
import jakarta.validation.Valid;
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
    public ResponseEntity<ImagenNoticiaResponse> agregarImagen(
            @Valid @RequestBody ImagenNoticiaRequest imagenNoticiaRequest,
            @RequestParam Long idNoticia) {

        ImagenNoticiaResponse imagenActualizada = imagenServicio.agregarImagen(imagenNoticiaRequest, idNoticia);

        return ResponseEntity.status(HttpStatus.CREATED).body(imagenActualizada);
    }

    @GetMapping("/imagen/{idNoticia}")
    public ResponseEntity<List<ImagenNoticiaResponse>> consultarImagenesPorNoticia(
            @PathVariable Long idNoticia){

        return ResponseEntity.ok(imagenServicio.consultarImagenesPorNoticia(idNoticia)

        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImagenNoticiaResponse> modificarImagen(
            @PathVariable Long id,
            @Valid @RequestBody ImagenNoticiaRequest imagenNoticiaRequest){

        ImagenNoticiaResponse imagenModificada = imagenServicio.modificarImagen(id, imagenNoticiaRequest);

        return ResponseEntity.ok(imagenModificada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarImagen(
            @PathVariable Long id){

        imagenServicio.eliminarImagen(id);

        return ResponseEntity.noContent().build();
    }
}
