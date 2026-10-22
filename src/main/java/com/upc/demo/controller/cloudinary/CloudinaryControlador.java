package com.upc.demo.controller.cloudinary;

import com.upc.demo.service.cloudinary.CloudinaryServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/archivos")
public class CloudinaryControlador {

    private final CloudinaryServicio cloudinaryServicio;

    public CloudinaryControlador(
            CloudinaryServicio cloudinaryServicio
    ) {
        this.cloudinaryServicio = cloudinaryServicio;
    }

    @PostMapping(
            value = "/subir-imagen",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Map<String, String>> subirImagen(
            @RequestParam("archivo") MultipartFile archivo
    ) {
        String url = cloudinaryServicio.subirImagen(archivo);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of("url", url)
        );
    }
}
