package com.upc.demo.controller.resoluciones;

import com.upc.demo.entity.resoluciones.Carrera;
import com.upc.demo.service.resoluciones.ICarreraServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carreras")
public class CarreraController {

    @Autowired
    private ICarreraServicio carreraServicio;

    /**
     * Obtiene todas las carreras.
     */
    @GetMapping("/todos")
    public ResponseEntity<List<Carrera>> listarTodos() {

        return ResponseEntity.ok(
                carreraServicio.listarTodos()
        );
    }

    /**
     * Obtiene una carrera por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Carrera> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                carreraServicio.buscarPorId(id)
        );
    }

    /**
     * Crea una nueva carrera.
     */
    @PostMapping
    public ResponseEntity<Carrera> guardar(
            @RequestBody Carrera carrera) {

        return ResponseEntity.ok(
                carreraServicio.guardar(carrera)
        );
    }

    /**
     * Actualiza una carrera existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Carrera> actualizar(
            @PathVariable Long id,
            @RequestBody Carrera carrera) {

        return ResponseEntity.ok(
                carreraServicio.actualizar(id, carrera)
        );
    }

    /**
     * Elimina una carrera.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        carreraServicio.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}