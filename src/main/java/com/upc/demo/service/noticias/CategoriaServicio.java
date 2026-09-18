package com.upc.demo.service.noticias;

import com.upc.demo.dto.request.noticias.CategoriaNoticiaRequest;
import com.upc.demo.dto.response.noticias.CategoriaNoticiaResponse;
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
    public List<CategoriaNoticiaResponse> listarCategoria() {

        List<CategoriaNoticia> categorias = categoriaRepo.findAll();

        if (categorias.isEmpty()) {
            throw new RuntimeException(
                    "No existen categorías registradas en el sistema."
            );
        }

        return categorias.stream()
                .map(categoria -> {
                    CategoriaNoticiaResponse response =
                            new CategoriaNoticiaResponse();

                    response.setId(categoria.getId());
                    response.setNombre(categoria.getNombre());

                    return response;
                })
                .toList();
    }

    @Override
    public CategoriaNoticiaResponse crearCategoria(CategoriaNoticiaRequest categoriaNoticiaRequest) {

        if (categoriaNoticiaRequest.getNombre() == null ||
                categoriaNoticiaRequest.getNombre().trim().isEmpty()) {

            throw new IllegalArgumentException("El nombre de la categoría es obligatorio");
        }

        Optional<CategoriaNoticia> categoriaExistente =
                categoriaRepo.findByNombreIgnoreCase(categoriaNoticiaRequest.getNombre());

        if (categoriaExistente.isPresent()) {
            throw new RuntimeException("Ya existe una categoría con ese nombre");
        }

        CategoriaNoticia categoria = new CategoriaNoticia();
        categoria.setNombre(categoriaNoticiaRequest.getNombre());

        CategoriaNoticia categoriaGuardada = categoriaRepo.save(categoria);

        CategoriaNoticiaResponse response = new CategoriaNoticiaResponse();
        response.setId(categoriaGuardada.getId());
        response.setNombre(categoriaGuardada.getNombre());

        return response;
    }

    @Override
    public CategoriaNoticiaResponse modificarCategoria(
            Long idCategoria,
            CategoriaNoticiaRequest categoriaNoticiaRequest) {

        CategoriaNoticia categoriaExistente = categoriaRepo.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        if (categoriaNoticiaRequest.getNombre() == null ||
                categoriaNoticiaRequest.getNombre().trim().isEmpty()) {

            throw new IllegalArgumentException("El nombre de la categoría es obligatorio");
        }

        String nombre = categoriaNoticiaRequest.getNombre().trim();

        Optional<CategoriaNoticia> categoriaDuplicada =
                categoriaRepo.findByNombreIgnoreCase(nombre);

        if (categoriaDuplicada.isPresent() &&
                !categoriaDuplicada.get().getId().equals(idCategoria)) {

            throw new IllegalArgumentException("Ya existe una categoría con ese nombre");
        }

        categoriaExistente.setNombre(nombre);

        CategoriaNoticia categoriaGuardada = categoriaRepo.save(categoriaExistente);

        CategoriaNoticiaResponse response = new CategoriaNoticiaResponse();
        response.setId(categoriaGuardada.getId());
        response.setNombre(categoriaGuardada.getNombre());

        return response;
    }

    @Override
    public CategoriaNoticiaResponse consultarCategoria(Long idCategoria) {

        CategoriaNoticia categoria = categoriaRepo.findById(idCategoria)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No existe una categoría con el ID: " + idCategoria
                        )
                );

        CategoriaNoticiaResponse response = new CategoriaNoticiaResponse();
        response.setId(categoria.getId());
        response.setNombre(categoria.getNombre());

        return response;
    }

    @Override
    public void eliminarCategoria(Long idNoticia) {

        CategoriaNoticia categoriaNoticia = categoriaRepo.findById(idNoticia).orElseThrow(() -> new RuntimeException("No existe una categoria con el ID: " + idNoticia));

        categoriaRepo.delete(categoriaNoticia);

    }


}
