package com.upc.demo.controller.resoluciones;

import com.upc.demo.entity.resoluciones.Comentario;
import com.upc.demo.service.resoluciones.IComentarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    @Autowired
    private IComentarioServicio comentarioServicio;

    /**
     * Obtiene todos los comentarios.
     */
    @GetMapping("/todos")
    public ResponseEntity<List<Comentario>> listarTodos() {

        return ResponseEntity.ok(
                comentarioServicio.listarTodos()
        );
    }

    /**
     * Obtiene un comentario por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Comentario> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                comentarioServicio.buscarPorId(id)
        );
    }

    /**
     * Crea un nuevo comentario.
     */
    @PostMapping
    public ResponseEntity<Comentario> guardar(
            @RequestBody Comentario comentario) {

        return ResponseEntity.ok(
                comentarioServicio.guardar(comentario)
        );
    }

    /**
     * Actualiza un comentario existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Comentario> actualizar(
            @PathVariable Long id,
            @RequestBody Comentario comentario) {

        return ResponseEntity.ok(
                comentarioServicio.actualizar(id, comentario)
        );
    }

    /**
     * Elimina un comentario.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        comentarioServicio.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}