package com.upc.demo.dto.response.noticias;


import com.upc.demo.entity.noticias.EstadoNoticia;
import com.upc.demo.dto.response.usuario.UsuarioResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public class NoticiaResponse {

    private Long id;
    private String titulo;
    private String cuerpo;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaPublicacion;
    private LocalDateTime fechaActualizacion;

    private EstadoNoticia estado;

    private CategoriaNoticiaResponse categoriaNoticia;
    private UsuarioResponseDTO autor;

    private List<ImagenNoticiaResponse> imagenes;
    private List<ArchivoNoticiaResponse> archivos;
}