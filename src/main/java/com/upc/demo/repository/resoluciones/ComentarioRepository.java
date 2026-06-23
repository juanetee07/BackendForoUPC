package com.upc.demo.repository.resoluciones;

import com.upc.demo.entity.resoluciones.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    /**
     * Obtiene todos los comentarios
     * asociados a una resolución.
     */
    List<Comentario> findByResolucionIdResolucion(Long idResolucion);

    /**
     * Obtiene todos los comentarios
     * realizados por un usuario.
     */
    List<Comentario> findByUsuarioIdUsuario(Long idUsuario);

    /**
     * Obtiene comentarios según su estado.
     */
    List<Comentario> findByEstado(String estado);

    /**
     * Obtiene comentarios pertenecientes
     * a una carrera específica.
     */
    List<Comentario> findByCarreraIdCarrera(Long idCarrera);

}