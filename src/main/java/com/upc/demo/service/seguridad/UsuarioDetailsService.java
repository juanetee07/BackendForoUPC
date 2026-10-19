package com.upc.demo.service.seguridad;

import com.upc.demo.entity.usuarios.Usuario;
import com.upc.demo.repository.usuarios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            throw new UsernameNotFoundException(
                    "Credenciales inválidas"
            );
        }

        if (!"ACTIVO".equalsIgnoreCase(usuario.getEstado())) {
            throw new DisabledException("El usuario no está activo");
        }

        if (usuario.getRol() == null
                || usuario.getRol().getNombre() == null) {
            throw new UsernameNotFoundException(
                    "El usuario no tiene un rol asignado"
            );
        }

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getContraseña())
                .authorities(
                        "ROLE_" + usuario.getRol().getNombre()
                )
                .build();
    }
}