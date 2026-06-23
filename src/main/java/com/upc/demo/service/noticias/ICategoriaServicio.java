package com.upc.demo.service.noticias;

import com.upc.demo.entity.noticias.CategoriaNoticia;

import java.util.List;

public interface ICategoriaServicio {

    List<CategoriaNoticia> listarCategoria();

    CategoriaNoticia crearCategoria(CategoriaNoticia categoriaNoticia);

    CategoriaNoticia modificarCategoria(Long idCategoria, CategoriaNoticia categoriaModificada);

    CategoriaNoticia consultarCategoria(Long idCategoria);

    CategoriaNoticia eliminarCategoria(Long idNoticia);
}
