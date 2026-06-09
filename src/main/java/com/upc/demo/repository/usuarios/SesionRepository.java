package com.upc.demo.repository.usuarios;

import com.upc.demo.entity.usuarios.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SesionRepository extends JpaRepository<Sesion, Long> {
    List<Sesion> findByUsuarioIdUsuario(Long usuarioId);
    List<Sesion> findByActiva(Boolean activa);
}