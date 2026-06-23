package com.upc.demo.service.resoluciones;

import com.upc.demo.entity.resoluciones.Resolucion;
import com.upc.demo.repository.resoluciones.ResolucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResolucionServicio implements IResolucionServicio {

    @Autowired
    private ResolucionRepository resolucionRepository;

    @Override
    public Resolucion guardar(Resolucion resolucion) {

        validarNuevaResolucion(resolucion);

        if (resolucionRepository.existsByNumeroResolucion(
                resolucion.getNumeroResolucion())) {

            throw new IllegalArgumentException(
                    "Ya existe una resolución con el número: "
                            + resolucion.getNumeroResolucion()
            );
        }

        return resolucionRepository.save(resolucion);
    }

    @Override
    public Resolucion buscarPorId(Long id) {

        return resolucionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No existe una resolución con ID: " + id
                        ));
    }

    @Override
    public List<Resolucion> listarTodos() {

        return resolucionRepository.findAll();
    }

    @Override
    public Resolucion actualizar(Long id, Resolucion datosActualizados) {

        Resolucion resolucion = buscarPorId(id);

        validarActualizacion(datosActualizados);

        resolucion.setTitulo(datosActualizados.getTitulo());
        resolucion.setDescripcion(datosActualizados.getDescripcion());
        resolucion.setCategoria(datosActualizados.getCategoria());
        resolucion.setObservaciones(datosActualizados.getObservaciones());
        resolucion.setEstado(datosActualizados.getEstado());
        resolucion.setVersionActual(datosActualizados.getVersionActual());

        return resolucionRepository.save(resolucion);
    }

    @Override
    public void eliminar(Long id) {

        Resolucion resolucion = buscarPorId(id);

        // Eliminación lógica según regla de negocio
        resolucion.setEstado("ARCHIVADA");

        resolucionRepository.save(resolucion);
    }

    /**
     * Validaciones para crear una nueva resolución.
     */
    private void validarNuevaResolucion(Resolucion resolucion) {

        if (resolucion.getNumeroResolucion() == null
                || resolucion.getNumeroResolucion().isBlank()) {

            throw new IllegalArgumentException(
                    "El número de resolución es obligatorio."
            );
        }

        if (resolucion.getTitulo() == null
                || resolucion.getTitulo().isBlank()) {

            throw new IllegalArgumentException(
                    "El título es obligatorio."
            );
        }

        if (resolucion.getCategoria() == null) {

            throw new IllegalArgumentException(
                    "La categoría es obligatoria."
            );
        }

        if (resolucion.getAutor() == null) {

            throw new IllegalArgumentException(
                    "El autor es obligatorio."
            );
        }

        if (resolucion.getFechaCreacion() == null) {

            throw new IllegalArgumentException(
                    "La fecha de creación es obligatoria."
            );
        }
    }

    /**
     * Validaciones para actualizar una resolución.
     */
    private void validarActualizacion(Resolucion resolucion) {

        if (resolucion.getTitulo() == null
                || resolucion.getTitulo().isBlank()) {

            throw new IllegalArgumentException(
                    "El título es obligatorio."
            );
        }

        if (resolucion.getCategoria() == null) {

            throw new IllegalArgumentException(
                    "La categoría es obligatoria."
            );
        }
    }
}