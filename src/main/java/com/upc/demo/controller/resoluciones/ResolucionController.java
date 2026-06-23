package com.upc.demo.controller.resoluciones;

import com.upc.demo.entity.resoluciones.Resolucion;
import com.upc.demo.service.resoluciones.IResolucionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resoluciones")
public class ResolucionController {

    @Autowired
    private IResolucionServicio resolucionServicio;

    /**
     * Obtiene todas las resoluciones.
     */
    @GetMapping("/todos")
    public ResponseEntity<List<Resolucion>> listarTodos() {

        return ResponseEntity.ok(
                resolucionServicio.listarTodos()
        );
    }

    /**
     * Obtiene una resolución por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Resolucion> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                resolucionServicio.buscarPorId(id)
        );
    }

    /**
     * Crea una nueva resolución.
     */
    @PostMapping
    public ResponseEntity<Resolucion> guardar(
            @RequestBody Resolucion resolucion) {

        return ResponseEntity.ok(
                resolucionServicio.guardar(resolucion)
        );
    }

    /**
     * Actualiza una resolución existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Resolucion> actualizar(
            @PathVariable Long id,
            @RequestBody Resolucion resolucion) {

        return ResponseEntity.ok(
                resolucionServicio.actualizar(id, resolucion)
        );
    }

    /**
     * Archiva una resolución.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        resolucionServicio.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}