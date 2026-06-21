package com.upc.demo.service.resoluciones;

import com.upc.demo.entity.resoluciones.Comentario;
import com.upc.demo.repository.resoluciones.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioServicio implements IComentarioServicio {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Override
    public Comentario guardar(Comentario comentario) {

        validarComentario(comentario);

        return comentarioRepository.save(comentario);
    }

    @Override
    public Comentario buscarPorId(Long id) {

        return comentarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No existe un comentario con ID: " + id
                        ));
    }

    @Override
    public List<Comentario> listarTodos() {

        return comentarioRepository.findAll();
    }

    @Override
    public Comentario actualizar(Long id, Comentario datosActualizados) {

        Comentario comentario = buscarPorId(id);

        validarComentario(datosActualizados);

        comentario.setCarrera(datosActualizados.getCarrera());
        comentario.setAnioCarrera(datosActualizados.getAnioCarrera());
        comentario.setContenido(datosActualizados.getContenido());
        comentario.setEstado(datosActualizados.getEstado());

        return comentarioRepository.save(comentario);
    }

    @Override
    public void eliminar(Long id) {

        Comentario comentario = buscarPorId(id);

        comentarioRepository.delete(comentario);
    }

    private void validarComentario(Comentario comentario) {

        if (comentario.getUsuario() == null) {
            throw new IllegalArgumentException(
                    "El usuario es obligatorio."
            );
        }

        if (comentario.getResolucion() == null) {
            throw new IllegalArgumentException(
                    "La resolución es obligatoria."
            );
        }

        if (comentario.getCarrera() == null) {
            throw new IllegalArgumentException(
                    "La carrera es obligatoria."
            );
        }

        if (comentario.getAnioCarrera() == null) {
            throw new IllegalArgumentException(
                    "El año de carrera es obligatorio."
            );
        }

        if (comentario.getContenido() == null
                || comentario.getContenido().isBlank()) {
            throw new IllegalArgumentException(
                    "El contenido del comentario es obligatorio."
            );
        }

        if (comentario.getFechaCreacion() == null) {
            throw new IllegalArgumentException(
                    "La fecha de creación es obligatoria."
            );
        }

        if (comentario.getEstado() == null
                || comentario.getEstado().isBlank()) {
            throw new IllegalArgumentException(
                    "El estado es obligatorio."
            );
        }
    }
}