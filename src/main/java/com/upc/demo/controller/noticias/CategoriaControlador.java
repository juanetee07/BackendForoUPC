package com.upc.demo.controller.noticias;

import com.upc.demo.entity.noticias.CategoriaNoticia;
import com.upc.demo.service.noticias.ICategoriaServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
