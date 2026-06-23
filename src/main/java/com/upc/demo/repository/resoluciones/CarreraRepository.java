package com.upc.demo.repository.resoluciones;

import com.upc.demo.entity.resoluciones.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarreraRepository extends JpaRepository<Carrera, Long> {

    /**
     * Busca una carrera por nombre exacto.
     */
    Optional<Carrera> findByNombre(String nombre);

    /**
     * Verifica si ya existe una carrera.
     */
    boolean existsByNombre(String nombre);

    /**
     * Permite búsquedas parciales.
     */
    List<Carrera> findByNombreContainingIgnoreCase(String nombre);

}