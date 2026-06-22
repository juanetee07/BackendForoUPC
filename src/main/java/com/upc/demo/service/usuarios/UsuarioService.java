package com.upc.demo.service.usuarios;

import com.upc.demo.entity.usuarios.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario guardar(Usuario usuario);

    Usuario buscarPorId(Long id);

    List<Usuario> listar();

    Usuario buscarPorEmail(String email);

    Usuario buscarPorDni(String dni);

    Usuario modificar(Long id, Usuario usuario);

    void eliminar(Long id);

}