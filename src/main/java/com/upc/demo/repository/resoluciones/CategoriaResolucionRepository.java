package com.upc.demo.repository.resoluciones;

import com.upc.demo.entity.resoluciones.CategoriaResolucion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaResolucionRepository
        extends JpaRepository<CategoriaResolucion, Long> {
}