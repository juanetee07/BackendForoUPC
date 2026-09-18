package com.upc.demo.controller.noticias;

import com.upc.demo.dto.request.noticias.NoticiaRequest;
import com.upc.demo.dto.response.noticias.NoticiaResponse;
import com.upc.demo.entity.noticias.Noticia;
import com.upc.demo.service.noticias.INoticiaServicio;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<NoticiaResponse>> listarNoticias() {

        List<NoticiaResponse> noticias = noticiaServicio.listarNoticias();

        return ResponseEntity.ok(noticias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoticiaResponse> consultarNoticia(
            @PathVariable Long id){

        return ResponseEntity.ok(noticiaServicio.consultarNoticia(id)
        );
    }

    @PostMapping
    public ResponseEntity<NoticiaResponse> crearNoticia(
            @Valid @RequestBody NoticiaRequest noticiaRequest,
            @RequestParam Long usuarioId){

        NoticiaResponse nuevaNoticia = noticiaServicio.crearNoticia(noticiaRequest, usuarioId);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaNoticia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoticiaResponse> editarNoticia(
            @Valid @RequestBody NoticiaRequest noticiaRequest,
            @PathVariable Long id,
            @RequestParam Long idAutor){

        NoticiaResponse noticiaActualizada = noticiaServicio.editarNoticia(id, noticiaRequest, idAutor);

        return ResponseEntity.ok(noticiaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNoticia(
            @PathVariable Long id){

        noticiaServicio.eliminarNoticia(id);

        return ResponseEntity.noContent().build();
    }
}
