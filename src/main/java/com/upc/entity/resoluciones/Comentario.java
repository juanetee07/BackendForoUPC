package com.upc.demo.entity.resoluciones;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comentarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private Long idComentario;

    @ManyToOne
    @JoinColumn(name = "id_resolucion", nullable = false)
    private Comentario resolucion;

    @ManyToOne
    @JoinColumn(name = "id_carrera", nullable = false)
    private Comentario carrera;

    // FK pendiente hasta que exista la entidad Usuario
    // @ManyToOne
    // @JoinColumn(name = "id_usuario")
    // private Usuario usuario;

    @Column(name = "anio_carrera", nullable = false)
    private Integer anioCarrera;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contenido;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false, length = 50)
    private String estado;
}