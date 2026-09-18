package com.upc.demo.dto.response.noticias;


import com.upc.demo.entity.noticias.EstadoNoticia;
import com.upc.demo.dto.response.usuarios.UsuarioResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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