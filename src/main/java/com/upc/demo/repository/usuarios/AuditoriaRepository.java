package com.upc.demo.repository.usuarios;

import com.upc.demo.entity.usuarios.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {
    List<Auditoria> findByUsuarioIdUsuario(Long usuarioId);
    List<Auditoria> findByEntidad(String entidad);
    List<Auditoria> findByAccion(String accion);
    List<Auditoria> findByFechaHoraBetween(LocalDateTime inicio,
                                           LocalDateTime fin);
}