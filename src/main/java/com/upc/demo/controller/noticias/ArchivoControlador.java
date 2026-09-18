package com.upc.demo.controller.noticias;

import com.upc.demo.dto.request.noticias.ArchivoNoticiaRequest;
import com.upc.demo.dto.response.noticias.ArchivoNoticiaResponse;
import com.upc.demo.entity.noticias.ArchivoNoticia;
import com.upc.demo.repository.noticias.ArchivoNoticiaRepositorio;
import com.upc.demo.service.noticias.IArchivoServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/archivo-noticias")
public class ArchivoControlador {

    @Autowired
    private IArchivoServicio archivoServicio;

    @PostMapping
    public ResponseEntity<ArchivoNoticiaResponse> agregarArchivo(
            @RequestParam Long idNoticia,
            @Valid @RequestBody ArchivoNoticiaRequest archivoNoticiaRequest) {

        ArchivoNoticiaResponse nuevoArchivo = archivoServicio.agregarArchivo(archivoNoticiaRequest, idNoticia);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoArchivo);
    }

    @GetMapping("/archivo/{idNoticia}")
    public ResponseEntity<List<ArchivoNoticiaResponse>> consultarArchivosDeNoticias(
            @PathVariable Long idNoticia){

        return ResponseEntity.ok(archivoServicio.consultarArchivosDeNoticias(idNoticia)
                
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArchivoNoticiaResponse> modificarArchivo(
            @PathVariable Long id,
            @Valid @RequestBody ArchivoNoticiaRequest archivoNoticiaRequest){

        ArchivoNoticiaResponse archivoModificado = archivoServicio.modificarArchivo(id, archivoNoticiaRequest);

        return ResponseEntity.ok(archivoModificado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarArchivo(
            @PathVariable Long id){

        archivoServicio.eliminarArchivo(id);

        return ResponseEntity.noContent().build();
    }

}
