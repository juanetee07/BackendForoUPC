package com.upc.demo.controller.noticias;

import com.upc.demo.dto.request.noticias.CategoriaNoticiaRequest;
import com.upc.demo.dto.response.noticias.CategoriaNoticiaResponse;
import com.upc.demo.entity.noticias.CategoriaNoticia;
import com.upc.demo.repository.noticias.CategoriaNoticiaRepositorio;
import com.upc.demo.service.noticias.ICategoriaServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categoria-noticias")
public class CategoriaControlador {

    @Autowired
    private ICategoriaServicio categoriaServicio;

    @GetMapping("/todos")
    public ResponseEntity<List<CategoriaNoticiaResponse>> listarCategoria() {

        List<CategoriaNoticiaResponse> categoriaNoticias =
                categoriaServicio.listarCategoria();

        return ResponseEntity.ok(categoriaNoticias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaNoticiaResponse> consultarCategoria(
            @PathVariable Long id){

        return ResponseEntity.ok(categoriaServicio.consultarCategoria(id)
        );
    }

    @PostMapping
    public ResponseEntity<CategoriaNoticiaResponse> crearCategoria(
            @Valid @RequestBody CategoriaNoticiaRequest categoriaNoticiaRequest) {

        CategoriaNoticiaResponse nuevaCategoria = categoriaServicio.crearCategoria(categoriaNoticiaRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaNoticiaResponse> modificarCategoria(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaNoticiaRequest categoriaNoticiaRequest){

        CategoriaNoticiaResponse categoriaActualizada = categoriaServicio.modificarCategoria(id, categoriaNoticiaRequest);

        return ResponseEntity.ok(categoriaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(
            @PathVariable Long id){

        categoriaServicio.eliminarCategoria(id);

        return ResponseEntity.noContent().build();
    }
}
