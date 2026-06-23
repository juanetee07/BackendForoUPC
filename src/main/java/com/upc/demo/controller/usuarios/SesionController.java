package com.upc.demo.controller.usuarios;

import com.upc.demo.entity.usuarios.Sesion;
import com.upc.demo.service.usuarios.SesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sesiones")
public class SesionController {

    @Autowired
    private SesionService sesionService;

    @GetMapping("/obtener/todos")
    public ResponseEntity<List<Sesion>> listarTodos() {
        return ResponseEntity.ok(sesionService.listar());
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Sesion sesion = sesionService.buscarPorId(id);
            return ResponseEntity.ok(sesion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> guardar(@RequestBody Sesion sesion) {
        try {
            Sesion nuevaSesion = sesionService.guardar(sesion);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(nuevaSesion);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody Sesion sesion) {
        try {
            Sesion sesionActualizada = sesionService.modificar(id, sesion);
            return ResponseEntity.ok(sesionActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            sesionService.eliminar(id);
            return ResponseEntity.ok("Sesión eliminada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}