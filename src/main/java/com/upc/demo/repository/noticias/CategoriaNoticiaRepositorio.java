package com.upc.demo.repository.noticias;

import com.upc.demo.entity.noticias.CategoriaNoticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaNoticiaRepositorio extends JpaRepository<CategoriaNoticia, Long> {

    /*Buscar categorias por nombre*/
    CategoriaNoticia findByNombre(String nombre);
}
