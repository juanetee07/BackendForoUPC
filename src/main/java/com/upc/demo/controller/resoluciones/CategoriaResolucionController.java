package com.upc.demo.controller.resoluciones;

import com.upc.demo.entity.resoluciones.CategoriaResolucion;
import com.upc.demo.service.resoluciones.ICategoriaResolucionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaResolucionController {

    @Autowired
    private ICategoriaResolucionServicio categoriaServicio;

    /**
     * Obtiene todas las categorías.
     */
    @GetMapping("/todos")
    public ResponseEntity<List<CategoriaResolucion>> listarTodos() {

        return ResponseEntity.ok(
                categoriaServicio.listarTodos()
        );
    }

    /**
     * Obtiene una categoría por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResolucion> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                categoriaServicio.buscarPorId(id)
        );
    }

    /**
     * Crea una nueva categoría.
     */
    @PostMapping
    public ResponseEntity<CategoriaResolucion> guardar(
            @RequestBody CategoriaResolucion categoria) {

        return ResponseEntity.ok(
                categoriaServicio.guardar(categoria)
        );
    }

    /**
     * Actualiza una categoría existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResolucion> actualizar(
            @PathVariable Long id,
            @RequestBody CategoriaResolucion categoria) {

        return ResponseEntity.ok(
                categoriaServicio.actualizar(id, categoria)
        );
    }

    /**
     * Elimina una categoría.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        categoriaServicio.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}