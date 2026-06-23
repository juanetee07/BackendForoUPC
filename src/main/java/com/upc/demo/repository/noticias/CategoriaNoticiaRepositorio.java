package com.upc.demo.repository.noticias;

import com.upc.demo.entity.noticias.CategoriaNoticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaNoticiaRepositorio extends JpaRepository<CategoriaNoticia, Long> {

    /*Buscar categorias por nombre*/
    Optional<CategoriaNoticia> findByNombre(String nombre);

    /*Buscar categoria ignorando mayusculas, minusculas*/
    Optional<CategoriaNoticia> findByNombreIgnoreCase(String nombre);

    boolean existsByNombreIgnoreCase(String nombre);
}
