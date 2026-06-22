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
        return rolRepository.save(rol);
    }

    @Override
    public Rol buscarPorId(Long id) {
        return rolRepository.findById(id).orElse(null);
    }

    @Override
    public List<Rol> listar() {
        return rolRepository.findAll();
    }

    @Override
    public Rol modificar(Long id, Rol rol) {
        Rol existente = rolRepository.findById(id).orElse(null);

        if (existente != null) {
            existente.setNombre(rol.getNombre());
            return rolRepository.save(existente);
        }

        return null;
    }

    @Override
    public void eliminar(Long id) {
        rolRepository.deleteById(id);
    }
}