package com.upc.demo.service.usuarios.impl;

import com.upc.demo.entity.usuarios.Rol;
import com.upc.demo.repository.usuarios.RolRepository;
import com.upc.demo.service.usuarios.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Rol guardar(Rol rol) {

        if (rol.getNombre() == null || rol.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del rol es obligatorio");
        }

        if (rolRepository.findByNombre(rol.getNombre()) != null) {
            throw new RuntimeException("El rol ya existe");
        }

        return rolRepository.save(rol);
    }

    @Override
    public Rol buscarPorId(Long id) {

        return rolRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Rol no encontrado"));
    }

    @Override
    public List<Rol> listar() {
        return rolRepository.findAll();
    }

    @Override
    public Rol modificar(Long id, Rol rol) {

        Rol existente = buscarPorId(id);

        existente.setNombre(rol.getNombre());

        return rolRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {

        buscarPorId(id);

        rolRepository.deleteById(id);
    }
}