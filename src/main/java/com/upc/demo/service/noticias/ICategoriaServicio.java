package com.upc.demo.service.noticias;

import com.upc.demo.entity.noticias.CategoriaNoticia;

public interface ICategoriaServicio {

    CategoriaNoticia crearCategoria(CategoriaNoticia categoriaNoticia);

    CategoriaNoticia modificarCategoria(Long idCategoria, CategoriaNoticia categoriaModificada);
}
