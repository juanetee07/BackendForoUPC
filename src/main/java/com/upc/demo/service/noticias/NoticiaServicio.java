package com.upc.demo.service.noticias;

import com.upc.demo.entity.noticias.CategoriaNoticia;
import com.upc.demo.entity.noticias.EstadoNoticia;
import com.upc.demo.entity.noticias.Noticia;
import com.upc.demo.entity.usuarios.Usuario;
import com.upc.demo.repository.noticias.CategoriaNoticiaRepositorio;
import com.upc.demo.repository.noticias.NoticiaRepositorio;
import com.upc.demo.repository.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NoticiaServicio implements INoticiaServicio{

    @Autowired
    private NoticiaRepositorio noticiaRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private CategoriaNoticiaRepositorio categoriaRepo;

    @Override
    public Noticia crearNoticia(Noticia noticia, Long idAutor) {

        /*Si el titulo de esa noticia no existe o si el titulo de esa noticia esta vacia entonces no podemos guardarlo*/
        if (noticia.getTitulo() == null || noticia.getTitulo().trim().isEmpty()){
            throw new RuntimeException("El título de la noticia es obligatorio");
        }
        /*Se aplica la misma logica que la anterior pero con Cuerpo*/
        if (noticia.getCuerpo() == null || noticia.getCuerpo().trim().isEmpty()) {
            throw new RuntimeException("El cuerpo de la noticia es obligatorio");
        }

        /*Si la categoria no existe*/
        if (noticia.getCategoriaNoticia() == null) {
            throw new RuntimeException("La noticia debe tener una categoría");
        }

        /*Si la categoria no es valida*/
        if (noticia.getCategoriaNoticia().getId() == null) {
            throw new RuntimeException("Debe seleccionar una categoría válida");
        }

        /*Buscar una categoria*/
        CategoriaNoticia categoria = categoriaRepo.findById(noticia.getCategoriaNoticia().getId()).orElseThrow(
                () -> new RuntimeException("Categoría no encontrada"));

        /*Se debe de vincular un autor a la noticia*/
        Usuario autor = usuarioRepo.findById(idAutor)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        noticia.setCategoriaNoticia(categoria);
        noticia.setAutor(autor);

        /*Se asigna la fecha de creacion y el estado predeterminado*/
        noticia.setFechaCreacion(LocalDateTime.now());
        noticia.setEstado(EstadoNoticia.BORRADOR);

        return noticiaRepo.save(noticia);
    }
}
