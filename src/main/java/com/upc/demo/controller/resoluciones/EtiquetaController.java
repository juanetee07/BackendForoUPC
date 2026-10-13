package com.upc.demo.controller.resoluciones;

import com.upc.demo.dto.request.resoluciones.EtiquetaRequestDTO;
import com.upc.demo.dto.response.resoluciones.EtiquetaResponseDTO;
import com.upc.demo.service.resoluciones.IEtiquetaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etiquetas")
public class EtiquetaController {

    @Autowired
    private IEtiquetaServicio etiquetaServicio;

    /**
     * Obtiene todas las etiquetas.
     */
    @GetMapping("/todos")
    public ResponseEntity<List<EtiquetaResponseDTO>> listarTodos() {

        return ResponseEntity.ok(
                etiquetaServicio.listarTodos()
        );
    }

    /**
     * Obtiene una etiqueta por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EtiquetaResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                etiquetaServicio.buscarPorId(id)
        );
    }

    /**
     * Crea una nueva etiqueta.
     */
    @PostMapping
    public ResponseEntity<EtiquetaResponseDTO> guardar(
            @RequestBody EtiquetaRequestDTO requestDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(etiquetaServicio.guardar(requestDTO));
    }

    /**
     * Actualiza una etiqueta existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EtiquetaResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody EtiquetaRequestDTO requestDTO) {

        return ResponseEntity.ok(
                etiquetaServicio.actualizar(id, requestDTO)
        );
    }

    /**
     * Elimina una etiqueta.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        etiquetaServicio.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}