package com.upc.demo.repository.resoluciones;

import com.upc.demo.entity.resoluciones.ArchivoResolucion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArchivoResolucionRepository
        extends JpaRepository<ArchivoResolucion, Long> {

    /**
     * Obtiene todos los archivos
     * asociados a una resolución.
     */
    List<ArchivoResolucion> findByResolucionIdResolucion(Long idResolucion);

    /**
     * Busca un archivo por nombre.
     */
    Optional<ArchivoResolucion> findByNombre(String nombre);

    /**
     * Verifica si existe un archivo
     * con determinado nombre.
     */
    boolean existsByNombre(String nombre);

    /**
     * Obtiene archivos según su tipo.
     *
     * Ejemplos:
     * pdf
     * docx
     * xlsx
     */
    List<ArchivoResolucion> findByTipo(String tipo);
}