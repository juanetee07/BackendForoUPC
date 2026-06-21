package com.upc.demo.repository.resoluciones;

import com.upc.demo.entity.resoluciones.ResolucionEtiqueta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResolucionEtiquetaRepository extends JpaRepository<ResolucionEtiqueta, Long> {

    /**
     * Obtiene todas las etiquetas asociadas
     * a una resolución específica.
     *
     * Ejemplo:
     * Resolución 15
     */
    List<ResolucionEtiqueta> findByResolucionIdResolucion(Long idResolucion);

    /**
     * Obtiene todas las resoluciones asociadas
     * a una etiqueta específica.
     *
     * Ejemplo:
     * Etiqueta "Becas"
     */
    List<ResolucionEtiqueta> findByEtiquetaIdEtiqueta(Long idEtiqueta);

}