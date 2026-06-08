package com.upc.repository.resoluciones;

import com.upc.entity.resoluciones.ResolucionEtiqueta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResolucionEtiquetaRepository
        extends JpaRepository<ResolucionEtiqueta, Long> {
}