package com.upc.demo.controller.resoluciones;

import com.upc.demo.dto.request.resoluciones.CategoriaResolucionRequestDTO;
import com.upc.demo.dto.response.resoluciones.CategoriaResolucionResponseDTO;
import com.upc.demo.service.resoluciones.ICategoriaResolucionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<CategoriaResolucionResponseDTO>> listarTodos() {

        return ResponseEntity.ok(
                categoriaServicio.listarTodos()
        );
    }

    /**
     * Obtiene una categoría por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResolucionResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                categoriaServicio.buscarPorId(id)
        );
    }

    /**
     * Crea una nueva categoría.
     */
    @PostMapping
    public ResponseEntity<CategoriaResolucionResponseDTO> guardar(
            @RequestBody CategoriaResolucionRequestDTO requestDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoriaServicio.guardar(requestDTO));
    }

    /**
     * Actualiza una categoría existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResolucionResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody CategoriaResolucionRequestDTO requestDTO) {

        return ResponseEntity.ok(
                categoriaServicio.actualizar(id, requestDTO)
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