package com.upc.demo.controller.resoluciones;

import com.upc.demo.dto.request.resoluciones.ComentarioRequestDTO;
import com.upc.demo.dto.response.resoluciones.ComentarioResponseDTO;
import com.upc.demo.service.resoluciones.IComentarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<ComentarioResponseDTO>> listarTodos() {

        return ResponseEntity.ok(
                comentarioServicio.listarTodos()
        );
    }

    /**
     * Obtiene un comentario por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ComentarioResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                comentarioServicio.buscarPorId(id)
        );
    }

    /**
     * Crea un nuevo comentario.
     */
    @PostMapping
    public ResponseEntity<ComentarioResponseDTO> guardar(
            @RequestBody ComentarioRequestDTO requestDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(comentarioServicio.guardar(requestDTO));
    }

    /**
     * Actualiza un comentario existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ComentarioResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody ComentarioRequestDTO requestDTO) {

        return ResponseEntity.ok(
                comentarioServicio.actualizar(id, requestDTO)
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