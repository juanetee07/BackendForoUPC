package com.upc.demo.controller.usuarios;

import com.upc.demo.dto.request.usuarios.LoginRequestDTO;
import com.upc.demo.dto.request.usuarios.UsuarioRequestDTO;
import com.upc.demo.dto.response.usuarios.UsuarioResponseDTO;
import com.upc.demo.service.usuarios.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/obtener/todos")
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listar());
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            UsuarioResponseDTO usuario = usuarioService.buscarPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/obtener/email/{email}")
    public ResponseEntity<?> buscarPorEmail(
            @PathVariable String email) {
        try {
            UsuarioResponseDTO usuario = usuarioService.buscarPorEmail(email);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/obtener/dni/{dni}")
    public ResponseEntity<?> buscarPorDni(
            @PathVariable String dni) {
        try {
            UsuarioResponseDTO usuario = usuarioService.buscarPorDni(dni);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Crear usuario mediante el CRUD
    @PostMapping("/crear")
    public ResponseEntity<?> guardar(
            @RequestBody UsuarioRequestDTO requestDTO) {
        try {
            UsuarioResponseDTO nuevoUsuario = usuarioService.guardar(requestDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Registro de usuario
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(
            @RequestBody UsuarioRequestDTO requestDTO) {
        try {
            UsuarioResponseDTO nuevoUsuario = usuarioService.registrar(requestDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Inicio de sesión
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequestDTO requestDTO) {
        try {
            UsuarioResponseDTO usuario = usuarioService.login(requestDTO);

            return ResponseEntity.ok(usuario);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No se pudo iniciar sesión");
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Long id,
            @RequestBody UsuarioRequestDTO requestDTO) {
        try {
            UsuarioResponseDTO usuarioActualizado = usuarioService.modificar(id, requestDTO);

            return ResponseEntity.ok(usuarioActualizado);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            usuarioService.eliminar(id);

            return ResponseEntity.ok("Usuario eliminado correctamente"
            );

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}