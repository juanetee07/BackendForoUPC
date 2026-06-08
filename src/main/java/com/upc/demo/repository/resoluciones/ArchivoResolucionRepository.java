package com.upc.demo.repository.resoluciones;

import com.upc.demo.entity.resoluciones.ArchivoResolucion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchivoResolucionRepository
        extends JpaRepository<ArchivoResolucion, Long> {
}