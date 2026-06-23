package com.upc.demo.controller.noticias;

import com.upc.demo.entity.noticias.CategoriaNoticia;
import com.upc.demo.service.noticias.ICategoriaServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categoria-noticias")
public class CategoriaControlador {

    private ICategoriaServicio categoriaServicio;

    @GetMapping("/todos")
    public ResponseEntity<List<CategoriaNoticia>> listarCategoria() {

        List<CategoriaNoticia> categoriaNoticias = categoriaServicio.listarCategoria();
        return ResponseEntity.ok(categoriaNoticias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaNoticia> consultarCategoria(
            @PathVariable Long id){

        return ResponseEntity.ok(categoriaServicio.consultarCategoria(id)
        );
    }

    @PostMapping
    public ResponseEntity<CategoriaNoticia> crearCategoria(
            @RequestBody CategoriaNoticia categoriaNoticia) {

        CategoriaNoticia nuevaCategoria = categoriaServicio.crearCategoria(categoriaNoticia);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaNoticia> modificarCategoria(
            @PathVariable Long id,
            @RequestBody CategoriaNoticia categoriaModificada){

        CategoriaNoticia categoriaActualizada = categoriaServicio.modificarCategoria(id, categoriaModificada);

        return ResponseEntity.ok(categoriaActualizada);
    }
}
