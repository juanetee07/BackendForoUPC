package com.upc.demo.controller.resoluciones;

import com.upc.demo.dto.request.resoluciones.ArchivoResolucionRequestDTO;
import com.upc.demo.dto.response.resoluciones.ArchivoResolucionResponseDTO;
import com.upc.demo.service.resoluciones.IArchivoResolucionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<ArchivoResolucionResponseDTO>> listarTodos() {

        return ResponseEntity.ok(
                archivoServicio.listarTodos()
        );
    }

    /**
     * Obtiene un archivo por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ArchivoResolucionResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                archivoServicio.buscarPorId(id)
        );
    }

    /**
     * Crea un nuevo archivo.
     */
    @PostMapping
    public ResponseEntity<ArchivoResolucionResponseDTO> guardar(
            @RequestBody ArchivoResolucionRequestDTO requestDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(archivoServicio.guardar(requestDTO));
    }

    /**
     * Actualiza un archivo existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ArchivoResolucionResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody ArchivoResolucionRequestDTO requestDTO) {

        return ResponseEntity.ok(
                archivoServicio.actualizar(id, requestDTO)
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