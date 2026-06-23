package com.upc.demo.controller.resoluciones;

import com.upc.demo.entity.resoluciones.Etiqueta;
import com.upc.demo.service.resoluciones.IEtiquetaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etiquetas")
public class EtiquetaController {

    @Autowired
    private IEtiquetaServicio etiquetaServicio;

    /**
     * Obtiene todas las etiquetas.
     */
    @GetMapping("/todos")
    public ResponseEntity<List<Etiqueta>> listarTodos() {

        return ResponseEntity.ok(
                etiquetaServicio.listarTodos()
        );
    }

    /**
     * Obtiene una etiqueta por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Etiqueta> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                etiquetaServicio.buscarPorId(id)
        );
    }

    /**
     * Crea una nueva etiqueta.
     */
    @PostMapping
    public ResponseEntity<Etiqueta> guardar(
            @RequestBody Etiqueta etiqueta) {

        return ResponseEntity.ok(
                etiquetaServicio.guardar(etiqueta)
        );
    }

    /**
     * Actualiza una etiqueta existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Etiqueta> actualizar(
            @PathVariable Long id,
            @RequestBody Etiqueta etiqueta) {

        return ResponseEntity.ok(
                etiquetaServicio.actualizar(id, etiqueta)
        );
    }

    /**
     * Elimina una etiqueta.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        etiquetaServicio.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}