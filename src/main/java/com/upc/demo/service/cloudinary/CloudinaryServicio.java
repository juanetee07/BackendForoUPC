package com.upc.demo.service.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Service
public class CloudinaryServicio {

    private static final long MAX_FILE_SIZE = 5L * 1024 * 1024;

    private static final Set<String> TIPOS_PERMITIDOS = Set.of(
            "image/jpeg",
            "image/png",
            "image/webp"
    );

    private final Cloudinary cloudinary;

    public CloudinaryServicio(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String subirImagen(MultipartFile archivo) {
        validarImagen(archivo);

        try {
            Map resultado = cloudinary.uploader().upload(
                    archivo.getBytes(),
                    ObjectUtils.asMap(
                            "folder", "upc/noticias",
                            "resource_type", "image"
                    )
            );

            Object url = resultado.get("secure_url");

            if (url == null) {
                throw new IllegalStateException(
                        "Cloudinary no devolvió la URL de la imagen."
                );
            }

            return url.toString();

        } catch (IOException e) {
            throw new IllegalStateException(
                    "No se pudo leer la imagen.",
                    e
            );
        } catch (Exception e) {
            e.printStackTrace();

            throw new IllegalStateException(
                    "No se pudo subir la imagen a Cloudinary.", e
            );
        }
    }

    private void validarImagen(MultipartFile archivo) {
        if (archivo == null || archivo.isEmpty()) {
            throw new IllegalArgumentException(
                    "Debés seleccionar una imagen."
            );
        }

        if (archivo.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException(
                    "La imagen no puede superar los 5 MB."
            );
        }

        String tipo = archivo.getContentType();

        if (tipo == null || !TIPOS_PERMITIDOS.contains(tipo)) {
            throw new IllegalArgumentException(
                    "Solo se permiten imágenes JPG, PNG o WEBP."
            );
        }
    }
}
