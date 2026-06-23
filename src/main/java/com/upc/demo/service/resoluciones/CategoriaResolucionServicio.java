package com.upc.demo.service.resoluciones;

import com.upc.demo.entity.resoluciones.CategoriaResolucion;
import com.upc.demo.repository.resoluciones.CategoriaResolucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaResolucionServicio
        implements ICategoriaResolucionServicio {

    @Autowired
    private CategoriaResolucionRepository categoriaRepository;

    @Override
    public CategoriaResolucion guardar(
            CategoriaResolucion categoria) {

        validarCategoria(categoria);

        if (categoriaRepository.existsByNombre(
                categoria.getNombre())) {

            throw new IllegalArgumentException(
                    "Ya existe una categoría con ese nombre."
            );
        }

        return categoriaRepository.save(categoria);
    }

    @Override
    public CategoriaResolucion buscarPorId(Long id) {

        return categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No existe una categoría con ID: " + id
                        ));
    }

    @Override
    public List<CategoriaResolucion> listarTodos() {

        return categoriaRepository.findAll();
    }

    @Override
    public CategoriaResolucion actualizar(
            Long id,
            CategoriaResolucion datosActualizados) {

        CategoriaResolucion categoria = buscarPorId(id);

        validarCategoria(datosActualizados);

        categoria.setNombre(
                datosActualizados.getNombre()
        );

        return categoriaRepository.save(categoria);
    }

    @Override
    public void eliminar(Long id) {

        CategoriaResolucion categoria = buscarPorId(id);

        categoriaRepository.delete(categoria);
    }

    private void validarCategoria(
            CategoriaResolucion categoria) {

        if (categoria.getNombre() == null
                || categoria.getNombre().isBlank()) {

            throw new IllegalArgumentException(
                    "El nombre de la categoría es obligatorio."
            );
        }
    }
}