package com.upc.demo.service.noticias;

import com.upc.demo.entity.noticias.CategoriaNoticia;
import com.upc.demo.repository.noticias.CategoriaNoticiaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServicio implements ICategoriaServicio{

    @Autowired
    private CategoriaNoticiaRepositorio categoriaRepo;


    @Override
    public List<CategoriaNoticia> listarCategoria() {
        List<CategoriaNoticia> categorias = categoriaRepo.findAll();

        if (categorias.isEmpty()) {
            throw new RuntimeException("No existen categorías registradas en el sistema.");
        }

        return categorias;
    }

    @Override
    public CategoriaNoticia crearCategoria(CategoriaNoticia categoriaNoticia) {

        if (categoriaNoticia.getNombre() == null || categoriaNoticia.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría es obligatorio");
        }

        Optional<CategoriaNoticia> categoriaExistente = categoriaRepo.findByNombreIgnoreCase(categoriaNoticia.getNombre());

        if (categoriaExistente.isPresent()) {
            throw new RuntimeException("Ya existe una categoría con ese nombre");
        }

        return categoriaRepo.save(categoriaNoticia);
    }

    @Override
    public CategoriaNoticia modificarCategoria(Long idCategoria, CategoriaNoticia categoriaModificada) {

        CategoriaNoticia categoriaExistente = categoriaRepo.findById(idCategoria).orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        if (categoriaModificada.getNombre() == null || categoriaModificada.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría es obligatorio");
        }

        String nombre = categoriaModificada.getNombre().trim();

        Optional<CategoriaNoticia> categoriaDuplicada = categoriaRepo.findByNombreIgnoreCase(nombre);

        if (categoriaDuplicada.isPresent() && !categoriaDuplicada.get().getId().equals(idCategoria)) {
            throw new IllegalArgumentException("Ya existe una categoría con ese nombre");
        }

        categoriaExistente.setNombre(nombre);

        return categoriaRepo.save(categoriaExistente);
    }

    @Override
    public List<CategoriaNoticia> consultarCategoria() {

        List<CategoriaNoticia> categorias = categoriaRepo.findAll();

        if (categorias.isEmpty()) {
            throw new RuntimeException("No existen categorías registradas");
        }

        return categorias;
    }


}
