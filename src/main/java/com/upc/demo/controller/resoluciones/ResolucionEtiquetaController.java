package com.upc.demo.controller.resoluciones;

import com.upc.demo.dto.request.resoluciones.ResolucionEtiquetaRequestDTO;
import com.upc.demo.dto.response.resoluciones.ResolucionEtiquetaResponseDTO;
import com.upc.demo.service.resoluciones.IResolucionEtiquetaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resolucion-etiquetas")
public class ResolucionEtiquetaController {

    @Autowired
    private IResolucionEtiquetaServicio resolucionEtiquetaServicio;

    /**
     * Obtiene todas las asociaciones.
     */
    @GetMapping("/todos")
    public ResponseEntity<List<ResolucionEtiquetaResponseDTO>> listarTodos() {

        return ResponseEntity.ok(
                resolucionEtiquetaServicio.listarTodos()
        );
    }

    /**
     * Obtiene una asociación por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResolucionEtiquetaResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                resolucionEtiquetaServicio.buscarPorId(id)
        );
    }

    /**
     * Crea una nueva asociación.
     */
    @PostMapping
    public ResponseEntity<ResolucionEtiquetaResponseDTO> guardar(
            @RequestBody ResolucionEtiquetaRequestDTO requestDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(resolucionEtiquetaServicio.guardar(requestDTO));
    }

    /**
     * Elimina una asociación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        resolucionEtiquetaServicio.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}