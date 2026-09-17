package com.upc.demo.controller.usuarios;

import com.upc.demo.dto.request.usuarios.SesionRequestDTO;
import com.upc.demo.dto.response.usuarios.SesionResponseDTO;
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
    public ResponseEntity<List<SesionResponseDTO>> listarTodos() {
        return ResponseEntity.ok(sesionService.listar());
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            SesionResponseDTO sesion = sesionService.buscarPorId(id);
            return ResponseEntity.ok(sesion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/obtener/usuario/{usuarioId}")
    public ResponseEntity<?> listarPorUsuario(@PathVariable Long usuarioId) {
        try {
            List<SesionResponseDTO> sesiones = sesionService.listarPorUsuario(usuarioId);
            return ResponseEntity.ok(sesiones);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/obtener/activas")
    public ResponseEntity<List<SesionResponseDTO>> listarActivas() {
        return ResponseEntity.ok(sesionService.listarActivas());
    }

    @PostMapping("/crear")
    public ResponseEntity<?> guardar(@RequestBody SesionRequestDTO requestDTO) {
        try {
            SesionResponseDTO nuevaSesion = sesionService.guardar(requestDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(nuevaSesion);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            sesionService.eliminar(id);
            return ResponseEntity.ok("Sesión cerrada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}