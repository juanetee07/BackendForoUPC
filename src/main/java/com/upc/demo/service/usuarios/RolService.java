package com.upc.demo.service.usuarios;

import com.upc.demo.entity.usuarios.Rol;
import java.util.List;

public interface RolService {

    Rol guardar(Rol rol);

    Rol buscarPorId(Long id);

    List<Rol> listar();

    Rol modificar(Long id, Rol rol);

    void eliminar(Long id);
}