package com.upc.demo.repository.usuarios;

import com.upc.demo.entity.usuarios.RecuperacionPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecuperacionPasswordRepository extends JpaRepository<RecuperacionPassword, Long> {
    RecuperacionPassword findByToken(String token);
    List<RecuperacionPassword> findByUsuarioIdUsuario(Long usuarioId);
}