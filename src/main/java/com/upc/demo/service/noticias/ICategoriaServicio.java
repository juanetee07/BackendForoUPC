package com.upc.demo.service.noticias;

import com.upc.demo.dto.request.noticias.CategoriaNoticiaRequest;
import com.upc.demo.dto.response.noticias.CategoriaNoticiaResponse;
import com.upc.demo.entity.noticias.CategoriaNoticia;

import java.util.List;

public interface ICategoriaServicio {

    List<CategoriaNoticiaResponse> listarCategoria();

    CategoriaNoticiaResponse crearCategoria(CategoriaNoticiaRequest categoriaNoticiaRequest);

    CategoriaNoticiaResponse modificarCategoria(Long idCategoria, CategoriaNoticiaRequest categoriaNoticiaRequest);

    CategoriaNoticiaResponse consultarCategoria(Long idCategoria);

    void eliminarCategoria(Long idNoticia);
}
