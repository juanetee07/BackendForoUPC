package com.upc.demo.controller.resoluciones;

import com.upc.demo.dto.request.resoluciones.ResolucionRequestDTO;
import com.upc.demo.dto.response.resoluciones.ResolucionResponseDTO;
import com.upc.demo.service.resoluciones.IResolucionServicio;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resoluciones")
public class ResolucionController {

    @Autowired
    private IResolucionServicio resolucionServicio;

    /**
     * Obtiene todas las resoluciones.
     */
    @GetMapping("/todos")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<ResolucionResponseDTO>> listarTodos() {

        return ResponseEntity.ok(
                resolucionServicio.listarTodos()
        );
    }

    @GetMapping("/ultimas-seis")
    public ResponseEntity<List<ResolucionResponseDTO>> listar6Resoluciones() {

        return ResponseEntity.ok(
                resolucionServicio.listar6Resoluciones()
        );
    }

    /**
     * Obtiene una resolución por ID.
     */
    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ResolucionResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                resolucionServicio.buscarPorId(id)
        );
    }

    /**
     * Crea una nueva resolución.
     */
    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ResolucionResponseDTO> guardar(
            @RequestBody ResolucionRequestDTO requestDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(resolucionServicio.guardar(requestDTO));
    }

    /**
     * Actualiza una resolución existente.
     */
    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ResolucionResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody ResolucionRequestDTO requestDTO) {

        return ResponseEntity.ok(
                resolucionServicio.actualizar(id, requestDTO)
        );
    }

    /**
     * Archiva una resolución.
     */
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        resolucionServicio.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
