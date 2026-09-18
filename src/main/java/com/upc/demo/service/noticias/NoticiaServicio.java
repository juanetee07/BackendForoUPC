package com.upc.demo.service.noticias;

import com.upc.demo.dto.request.noticias.NoticiaRequest;
import com.upc.demo.dto.response.noticias.NoticiaResponse;
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
import java.util.List;

@Service
public class NoticiaServicio implements INoticiaServicio{

    @Autowired
    private NoticiaRepositorio noticiaRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private CategoriaNoticiaRepositorio categoriaRepo;

    @Override
    public List<NoticiaResponse> listarNoticias() {

        List<NoticiaResponse> noticias = noticiaRepo.findAll()
                .stream()
                .filter(n -> n.getEstado() == EstadoNoticia.PUBLICADA)
                .map(n -> {
                    NoticiaResponse response = new NoticiaResponse();

                    response.setId(n.getId());
                    response.setTitulo(n.getTitulo());
                    response.setCuerpo(n.getCuerpo());
                    response.setFechaCreacion(n.getFechaCreacion());
                    response.setFechaPublicacion(n.getFechaPublicacion());
                    response.setFechaActualizacion(n.getFechaActualizacion());
                    response.setEstado(n.getEstado());

                    return response;
                })
                .toList();

        if (noticias.isEmpty()) {
            throw new RuntimeException("No hay noticias disponibles");
        }

        return noticias;
    }

    @Override
    public NoticiaResponse crearNoticia(NoticiaRequest noticiaRequest, Long usuarioId) {

        // Buscar la categoría
        CategoriaNoticia categoria = categoriaRepo.findById(noticiaRequest.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        // Buscar el autor
        Usuario autor = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        // Verificar que el usuario tenga un rol
        if (autor.getRol() == null) {
            throw new RuntimeException("El usuario no tiene un rol asignado");
        }

        // Verificar permisos
        String rol = autor.getRol().getNombre();

        if (!rol.equalsIgnoreCase("ADMINISTRADOR")
                && !rol.equalsIgnoreCase("PERSONAL_ESCUELA")) {
            throw new RuntimeException("No tiene permisos para crear noticias");
        }

        // Crear la entidad Noticia
        Noticia noticia = new Noticia();

        noticia.setTitulo(noticiaRequest.getTitulo());
        noticia.setCuerpo(noticiaRequest.getCuerpo());
        noticia.setCategoriaNoticia(categoria);
        noticia.setAutor(autor);
        noticia.setFechaCreacion(LocalDateTime.now());
        noticia.setEstado(EstadoNoticia.BORRADOR);

        // Guardar la noticia
        Noticia noticiaGuardada = noticiaRepo.save(noticia);

        // Crear respuesta
        NoticiaResponse response = new NoticiaResponse();

        response.setId(noticiaGuardada.getId());
        response.setTitulo(noticiaGuardada.getTitulo());
        response.setCuerpo(noticiaGuardada.getCuerpo());
        response.setFechaCreacion(noticiaGuardada.getFechaCreacion());
        response.setFechaPublicacion(noticiaGuardada.getFechaPublicacion());
        response.setFechaActualizacion(noticiaGuardada.getFechaActualizacion());
        response.setEstado(noticiaGuardada.getEstado());

        return response;
    }

    @Override
    public NoticiaResponse editarNoticia(
            Long idNoticia,
            NoticiaRequest noticiaActualizada,
            Long idAutor) {

        /* Buscar la noticia */
        Noticia noticiaExistente = noticiaRepo.findById(idNoticia)
                .orElseThrow(() ->
                        new RuntimeException("Noticia no encontrada"));

        /* Buscar el usuario */
        Usuario usuario = usuarioRepo.findById(idAutor)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));

        /* Buscar la categoría */
        CategoriaNoticia categoria = categoriaRepo.findById(noticiaActualizada.getCategoriaId())
                .orElseThrow(() ->
                        new RuntimeException("Categoría no encontrada"));

        /* Verificar que el usuario tenga un rol */
        if (usuario.getRol() == null) {
            throw new RuntimeException("El usuario no tiene un rol asignado");
        }

        String rol = usuario.getRol().getNombre();

        /* Verificar permisos */
        if (!rol.equalsIgnoreCase("ADMINISTRADOR")
                && !rol.equalsIgnoreCase("PERSONAL_ESCUELA")) {

            throw new RuntimeException(
                    "No tiene permisos para editar noticias"
            );
        }

        /* PERSONAL_ESCUELA solo puede editar sus propias noticias */
        if (rol.equalsIgnoreCase("PERSONAL_ESCUELA")
                && !noticiaExistente.getAutor()
                .getIdUsuario()
                .equals(usuario.getIdUsuario())) {

            throw new RuntimeException(
                    "Solo puede editar sus propias noticias"
            );
        }

        /* Actualizar los datos */
        noticiaExistente.setTitulo(noticiaActualizada.getTitulo());
        noticiaExistente.setCuerpo(noticiaActualizada.getCuerpo());
        noticiaExistente.setCategoriaNoticia(categoria);
        noticiaExistente.setFechaActualizacion(LocalDateTime.now());

        /* Guardar */
        Noticia noticiaGuardada = noticiaRepo.save(noticiaExistente);

        /* Convertir a Response */
        NoticiaResponse response = new NoticiaResponse();

        response.setId(noticiaGuardada.getId());
        response.setTitulo(noticiaGuardada.getTitulo());
        response.setCuerpo(noticiaGuardada.getCuerpo());
        response.setFechaCreacion(noticiaGuardada.getFechaCreacion());
        response.setFechaPublicacion(noticiaGuardada.getFechaPublicacion());
        response.setFechaActualizacion(noticiaGuardada.getFechaActualizacion());
        response.setEstado(noticiaGuardada.getEstado());

        return response;
    }

    @Override
    public Noticia publicarNoticia(Long idNoticia, Long idAutor) {

        Noticia noticia = noticiaRepo.findById(idNoticia).orElseThrow(() -> new RuntimeException("Noticia no encontrada"));

        Usuario usuario = usuarioRepo.findById(idAutor).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        /*Se debe de verificar si el usuario tiene asignado un rol*/
        if (usuario.getRol() == null) {
            throw new RuntimeException("El usuario no tiene un rol asignado");
        }

        String rol = usuario.getRol().getNombre();

        /*Se verifica que el usuario tenga los permisos necesarios */
        if (!rol.equalsIgnoreCase("ADMINISTRADOR") && !rol.equalsIgnoreCase("PERSONAL_ESCUELA")) {
            throw new RuntimeException("No tiene permisos para publicar noticias");
        }

        /*Se verifica si el usuario PERSONAL_ESCUELA redacto esa noticia para poder editarla*/
        if (rol.equalsIgnoreCase("PERSONAL_ESCUELA") && !noticia.getAutor().getIdUsuario().equals(usuario.getIdUsuario())) {
            throw new RuntimeException("Solo puede publicar sus propias noticias");
        }

        /*Verifica que el estado de la noticia no sea null*/
        if (noticia.getEstado() == null){
            throw new RuntimeException("Solo se puede publicar una noticia que se encuentre en un estado BORRADOR");
        }

        /*Verifica que el estado de la noticia no sea distinto a borrador*/
        if (noticia.getEstado() != EstadoNoticia.BORRADOR) {
            throw new RuntimeException("Solo se puede publicar una noticia en ESTADO BORRADOR");
        }

        noticia.setEstado(EstadoNoticia.PUBLICADA);
        noticia.setFechaPublicacion(LocalDateTime.now());
        return noticiaRepo.save(noticia);
    }

    @Override
    public Noticia cambiarEstado( Long idNoticia, EstadoNoticia nuevoEstado, Long idAutor) {

        if (nuevoEstado == null) {
            throw new IllegalArgumentException("El nuevo estado no puede ser nulo.");
        }

        Noticia noticia = noticiaRepo.findById(idNoticia).orElseThrow(() -> new RuntimeException("Noticia no encontrada"));

        Usuario usuario = usuarioRepo.findById(idAutor).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        /*Se debe de verificar si el usuario tiene asignado un rol*/
        if (usuario.getRol() == null || usuario.getRol().getNombre() == null) {
            throw new RuntimeException("El usuario no tiene un rol asignado");
        }

        String rol = usuario.getRol().getNombre();

        if (!rol.equalsIgnoreCase("ADMINISTRADOR")) {
            throw new RuntimeException(
                    "Solo un administrador puede cambiar el estado de una noticia");
        }

        if (noticia.getEstado() == nuevoEstado) {
            throw new RuntimeException("La noticia ya se encuentra en estado: " + nuevoEstado);
        }

        noticia.setEstado(nuevoEstado);
        noticia.setFechaActualizacion(LocalDateTime.now());

        return noticiaRepo.save(noticia);
    }

    @Override
    public NoticiaResponse consultarNoticia(Long idNoticia) {

        /* Verificar que la noticia exista */
        Noticia noticia = noticiaRepo.findById(idNoticia)
                .orElseThrow(() -> new RuntimeException("Noticia no encontrada"));

        /* Verificar que esté publicada */
        if (noticia.getEstado() != EstadoNoticia.PUBLICADA) {
            throw new RuntimeException(
                    "La noticia no se encuentra disponible para consulta"
            );
        }

        /* Convertir Noticia a NoticiaResponse */
        NoticiaResponse response = new NoticiaResponse();

        response.setId(noticia.getId());
        response.setTitulo(noticia.getTitulo());
        response.setCuerpo(noticia.getCuerpo());
        response.setFechaCreacion(noticia.getFechaCreacion());
        response.setFechaPublicacion(noticia.getFechaPublicacion());
        response.setFechaActualizacion(noticia.getFechaActualizacion());
        response.setEstado(noticia.getEstado());

        return response;
    }

    @Override
    public List<Noticia> buscarNoticiaPorTitulo(String titulo) {
        return noticiaRepo.findByTituloContainingIgnoreCase(titulo).stream().filter(n -> n.getEstado() == EstadoNoticia.PUBLICADA).toList();
    }

    @Override
    public List<Noticia> buscarNoticiaPorCategoria(Long categoriaId) {
        return noticiaRepo.findByCategoriaNoticiaId(categoriaId).stream().filter(noticia -> noticia.getEstado() == EstadoNoticia.PUBLICADA).toList();

    }

    @Override
    public List<Noticia> buscarNoticiaPorFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return noticiaRepo.findByFechaPublicacionBetween(fechaInicio, fechaFin).stream().filter(n -> n.getEstado() == EstadoNoticia.PUBLICADA).toList();
    }

    @Override
    public void eliminarNoticia(Long idNoticia) {

        Noticia noticia = noticiaRepo.findById(idNoticia).orElseThrow(() -> new RuntimeException("No existe una noticia con el ID: " + idNoticia));

        noticiaRepo.delete(noticia);

    }
}
