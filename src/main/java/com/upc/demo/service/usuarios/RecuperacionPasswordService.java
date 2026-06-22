package com.upc.demo.service.usuarios;

import com.upc.demo.entity.usuarios.RecuperacionPassword;

import java.util.List;

public interface RecuperacionPasswordService {

    RecuperacionPassword guardar(RecuperacionPassword recuperacionPassword);

    RecuperacionPassword buscarPorId(Long id);

    List<RecuperacionPassword> listar();

    RecuperacionPassword buscarPorToken(String token);

    RecuperacionPassword buscarPorUsuario(Long usuarioId);

    void eliminar(Long id);

}