package com.upc.demo.dto.response.resoluciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioResponseDTO {

    private Long idComentario;
    private Long idUsuario;
    private String nombreUsuario;
    private Long idResolucion;
    private String numeroResolucion;
    private Long idCarrera;
    private String nombreCarrera;
    private Integer anioCarrera;
    private String contenido;
    private LocalDateTime fechaCreacion;
    private String estado;
}
