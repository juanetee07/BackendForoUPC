package com.upc.demo.service.usuarios;

import com.upc.demo.entity.usuarios.Sesion;
import java.util.List;

public interface SesionService {

    Sesion guardar(Sesion sesion);

    Sesion buscarPorId(Long id);

    List<Sesion> listar();

    List<Sesion> buscarPorUsuario(Long usuarioId);

    List<Sesion> buscarPorActiva(Boolean activa);

    Sesion modificar(Long id, Sesion sesion);

    void eliminar(Long id);
}