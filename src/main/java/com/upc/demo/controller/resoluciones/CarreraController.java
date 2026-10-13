package com.upc.demo.controller.resoluciones;

import com.upc.demo.dto.request.resoluciones.CarreraRequestDTO;
import com.upc.demo.dto.response.resoluciones.CarreraResponseDTO;
import com.upc.demo.service.resoluciones.ICarreraServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<CarreraResponseDTO>> listarTodos() {

        return ResponseEntity.ok(
                carreraServicio.listarTodos()
        );
    }

    /**
     * Obtiene una carrera por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CarreraResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                carreraServicio.buscarPorId(id)
        );
    }

    /**
     * Crea una nueva carrera.
     */
    @PostMapping
    public ResponseEntity<CarreraResponseDTO> guardar(
            @RequestBody CarreraRequestDTO requestDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(carreraServicio.guardar(requestDTO));
    }

    /**
     * Actualiza una carrera existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CarreraResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody CarreraRequestDTO requestDTO) {

        return ResponseEntity.ok(
                carreraServicio.actualizar(id, requestDTO)
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