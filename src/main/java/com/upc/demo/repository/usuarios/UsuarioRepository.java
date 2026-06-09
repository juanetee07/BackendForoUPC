package com.upc.demo.repository.usuarios;

import com.upc.demo.entity.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
    List<Usuario> findByEstado(String estado);

    Usuario findByDni(String dni);
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);
    List<Usuario> findByApellidoContainingIgnoreCase(String apellido);
    List<Usuario> findByEstadoSolicitud(String estadoSolicitud);
}