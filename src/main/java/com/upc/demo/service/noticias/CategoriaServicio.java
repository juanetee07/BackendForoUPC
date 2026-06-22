package com.upc.demo.service.noticias;

import com.upc.demo.entity.noticias.CategoriaNoticia;
import com.upc.demo.repository.noticias.CategoriaNoticiaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaServicio implements ICategoriaServicio{

    @Autowired
    private CategoriaNoticiaRepositorio categoriaRepo;


    @Override
    public CategoriaNoticia crearCategoria(CategoriaNoticia categoriaNoticia) {

        if (categoriaNoticia.getNombre() == null ||
                categoriaNoticia.getNombre().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "El nombre de la categoría es obligatorio");
        }

        Optional<CategoriaNoticia> categoriaExistente = categoriaRepo.findByNombreIgnoreCase(categoriaNoticia.getNombre());

        if (categoriaExistente.isPresent()) {
            throw new RuntimeException(
                    "Ya existe una categoría con ese nombre");
        }

        return categoriaRepo.save(categoriaNoticia);
    }
}
