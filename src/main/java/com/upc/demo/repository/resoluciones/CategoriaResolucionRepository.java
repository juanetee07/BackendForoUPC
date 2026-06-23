package com.upc.demo.repository.resoluciones;

import com.upc.demo.entity.resoluciones.CategoriaResolucion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaResolucionRepository
        extends JpaRepository<CategoriaResolucion, Long> {

    /**
     * Busca una categoría por nombre exacto.
     */
    Optional<CategoriaResolucion> findByNombre(String nombre);

    /**
     * Verifica si una categoría ya existe.
     */
    boolean existsByNombre(String nombre);

    /**
     * Permite búsquedas parciales de categorías.
     */
    List<CategoriaResolucion> findByNombreContainingIgnoreCase(String nombre);

}