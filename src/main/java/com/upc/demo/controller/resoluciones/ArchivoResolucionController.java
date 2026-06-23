package com.upc.demo.controller.resoluciones;

import com.upc.demo.entity.resoluciones.ArchivoResolucion;
import com.upc.demo.service.resoluciones.IArchivoResolucionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/archivos")
public class ArchivoResolucionController {

    @Autowired
    private IArchivoResolucionServicio archivoServicio;

    /**
     * Obtiene todos los archivos.
     */
    @GetMapping("/todos")
    public ResponseEntity<List<ArchivoResolucion>> listarTodos() {

        return ResponseEntity.ok(
                archivoServicio.listarTodos()
        );
    }

    /**
     * Obtiene un archivo por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ArchivoResolucion> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                archivoServicio.buscarPorId(id)
        );
    }

    /**
     * Crea un nuevo archivo.
     */
    @PostMapping
    public ResponseEntity<ArchivoResolucion> guardar(
            @RequestBody ArchivoResolucion archivo) {

        return ResponseEntity.ok(
                archivoServicio.guardar(archivo)
        );
    }

    /**
     * Actualiza un archivo existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ArchivoResolucion> actualizar(
            @PathVariable Long id,
            @RequestBody ArchivoResolucion archivo) {

        return ResponseEntity.ok(
                archivoServicio.actualizar(id, archivo)
        );
    }

    /**
     * Elimina un archivo.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        archivoServicio.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}