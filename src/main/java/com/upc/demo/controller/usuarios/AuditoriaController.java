package com.upc.demo.controller.usuarios;

import com.upc.demo.entity.usuarios.Auditoria;
import com.upc.demo.service.usuarios.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auditorias")
public class AuditoriaController {

    @Autowired
    private AuditoriaService auditoriaService;

    @GetMapping("/obtener/todos")
    public ResponseEntity<List<Auditoria>> listarTodos() {
        return ResponseEntity.ok(auditoriaService.listar());
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Auditoria auditoria = auditoriaService.buscarPorId(id);
            return ResponseEntity.ok(auditoria);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> guardar(@RequestBody Auditoria auditoria) {
        try {
            Auditoria nuevaAuditoria = auditoriaService.guardar(auditoria);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(nuevaAuditoria);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            auditoriaService.eliminar(id);
            return ResponseEntity.ok("Auditoría eliminada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}