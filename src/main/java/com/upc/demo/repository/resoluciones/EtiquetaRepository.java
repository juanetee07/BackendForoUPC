package com.upc.demo.repository.resoluciones;

import com.upc.demo.entity.resoluciones.Etiqueta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EtiquetaRepository extends JpaRepository<Etiqueta, Long> {

    /**
     * Busca una etiqueta por su nombre exacto.
     */
    Optional<Etiqueta> findByNombre(String nombre);

    /**
     * Verifica si una etiqueta ya existe.
     */
    boolean existsByNombre(String nombre);

    /**
     * Permite búsquedas parciales para filtros
     * y autocompletado.
     */
    List<Etiqueta> findByNombreContainingIgnoreCase(String nombre);

}