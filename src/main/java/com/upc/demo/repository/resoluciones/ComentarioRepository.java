package com.upc.demo.repository.resoluciones;

import com.upc.demo.entity.resoluciones.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}