package com.upc.demo.service.resoluciones;

import com.upc.demo.entity.resoluciones.ArchivoResolucion;
import com.upc.demo.repository.resoluciones.ArchivoResolucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchivoResolucionServicio
        implements IArchivoResolucionServicio {

    @Autowired
    private ArchivoResolucionRepository archivoRepository;

    @Override
    public ArchivoResolucion guardar(
            ArchivoResolucion archivo) {

        validarArchivo(archivo);

        if (archivoRepository.existsByNombre(
                archivo.getNombre())) {

            throw new IllegalArgumentException(
                    "Ya existe un archivo con ese nombre."
            );
        }

        return archivoRepository.save(archivo);
    }

    @Override
    public ArchivoResolucion buscarPorId(Long id) {

        return archivoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No existe un archivo con ID: " + id
                        ));
    }

    @Override
    public List<ArchivoResolucion> listarTodos() {

        return archivoRepository.findAll();
    }

    @Override
    public ArchivoResolucion actualizar(
            Long id,
            ArchivoResolucion datosActualizados) {

        ArchivoResolucion archivo = buscarPorId(id);

        validarArchivo(datosActualizados);

        archivo.setNombre(
                datosActualizados.getNombre()
        );

        archivo.setUrl(
                datosActualizados.getUrl()
        );

        archivo.setTipo(
                datosActualizados.getTipo()
        );

        return archivoRepository.save(archivo);
    }

    @Override
    public void eliminar(Long id) {

        ArchivoResolucion archivo = buscarPorId(id);

        archivoRepository.delete(archivo);
    }

    private void validarArchivo(
            ArchivoResolucion archivo) {

        if (archivo.getResolucion() == null) {

            throw new IllegalArgumentException(
                    "La resolución es obligatoria."
            );
        }

        if (archivo.getNombre() == null
                || archivo.getNombre().isBlank()) {

            throw new IllegalArgumentException(
                    "El nombre del archivo es obligatorio."
            );
        }

        if (archivo.getUrl() == null
                || archivo.getUrl().isBlank()) {

            throw new IllegalArgumentException(
                    "La URL del archivo es obligatoria."
            );
        }

        if (archivo.getTipo() == null
                || archivo.getTipo().isBlank()) {

            throw new IllegalArgumentException(
                    "El tipo del archivo es obligatorio."
            );
        }
    }
}