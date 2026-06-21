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

        /*Se debe de verificar si el usuario tiene asignado un rol*/
        if (autor.getRol() == null) {
            throw new RuntimeException("El usuario no tiene un rol asignado");
        }

        String rol = autor.getRol().getNombre();

        /*Se verifica que el usuario tenga los permisos necesarios */
        if (!rol.equalsIgnoreCase("ADMINISTRADOR") && !rol.equalsIgnoreCase("PERSONAL_ESCUELA")) {
            throw new RuntimeException("No tiene permisos para crear noticias");
        }

        noticia.setCategoriaNoticia(categoria);
        noticia.setAutor(autor);

        /*Se asigna la fecha de creacion y el estado predeterminado*/
        noticia.setFechaCreacion(LocalDateTime.now());
        noticia.setEstado(EstadoNoticia.BORRADOR);

        /*Se guarda la noticia creada*/
        return noticiaRepo.save(noticia);
    }

    @Override
    public Noticia editarNoticia(Long idNoticia, Noticia noticiaActualizada, Long idAutor) {

        /*Se debe de buscar que la noticia exista*/
        Noticia noticiaExistente = noticiaRepo.findById(idNoticia).orElseThrow(
                () -> new RuntimeException("Noticia no encontrada"));

        /*Se debe de buscar el usuario que exista*/
        Usuario usuario = usuarioRepo.findById(idAutor).orElseThrow(
                () -> new RuntimeException("Usuario no encontrado"));

        /*Si el titulo de la noticia no existe o si el titulo de esa noticia esta vacia entonces no podemos guardar la actualizacion */
        if (noticiaActualizada.getTitulo() == null || noticiaActualizada.getTitulo().trim().isEmpty()) {
            throw new RuntimeException("El título de la noticia es obligatorio");
        }

        if (noticiaActualizada.getCuerpo() == null || noticiaActualizada.getCuerpo().trim().isEmpty()) {
            throw new RuntimeException("El cuerpo de la noticia es obligatorio");
        }

        if (noticiaActualizada.getCategoriaNoticia() == null || noticiaActualizada.getCategoriaNoticia().getId() == null) {
            throw new RuntimeException("Debe seleccionar una categoría válida");
        }

        CategoriaNoticia categoria = categoriaRepo.findById(noticiaActualizada.getCategoriaNoticia().getId()).orElseThrow(
                () -> new RuntimeException("Categoría no encontrada"));

        /*Se debe de verificar si el usuario tiene asignado un rol*/
        if (usuario.getRol() == null) {
            throw new RuntimeException("El usuario no tiene un rol asignado");
        }

        String rol = usuario.getRol().getNombre();

        /*Se verifica que el usuario tenga los permisos necesarios */
        if (!rol.equalsIgnoreCase("ADMINISTRADOR") && !rol.equalsIgnoreCase("PERSONAL_ESCUELA")) {
            throw new RuntimeException("No tiene permisos para editar noticias");
        }

        /*Se verifica si el usuario PERSONAL_ESCUELA redacto esa noticia para poder editarla*/
        if (rol.equalsIgnoreCase("PERSONAL_ESCUELA") && !noticiaExistente.getAutor().getIdUsuario().equals(usuario.getIdUsuario())) {
            throw new RuntimeException("Solo puede editar sus propias noticias");
        }

        /*Actualizamos*/
        noticiaExistente.setTitulo(noticiaActualizada.getTitulo());
        noticiaExistente.setCuerpo(noticiaActualizada.getCuerpo());
        noticiaExistente.setCategoriaNoticia(categoria);

        noticiaExistente.setFechaActualizacion(LocalDateTime.now());

        return noticiaRepo.save(noticiaExistente);
    }
}
