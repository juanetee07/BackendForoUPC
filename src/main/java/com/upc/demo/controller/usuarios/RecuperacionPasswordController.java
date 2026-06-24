package com.upc.demo.controller.usuarios;

import com.upc.demo.entity.usuarios.RecuperacionPassword;
import com.upc.demo.service.usuarios.RecuperacionPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recuperaciones")
public class RecuperacionPasswordController {

    @Autowired
    private RecuperacionPasswordService recuperacionPasswordService;

    @GetMapping("/obtener/todos")
    public ResponseEntity<List<RecuperacionPassword>> listarTodos() {
        return ResponseEntity.ok(recuperacionPasswordService.listar());
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            RecuperacionPassword recuperacion = recuperacionPasswordService.buscarPorId(id);
            return ResponseEntity.ok(recuperacion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> guardar(@RequestBody RecuperacionPassword recuperacionPassword) {
        try {
            RecuperacionPassword nuevaRecuperacion =
                    recuperacionPasswordService.guardar(recuperacionPassword);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(nuevaRecuperacion);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            recuperacionPasswordService.eliminar(id);
            return ResponseEntity.ok("Recuperación eliminada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}