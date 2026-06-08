package com.upc.demo.entity.resoluciones;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "resoluciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resolucion")
    private Long idResolucion;

    @Column(name = "numero_resolucion", nullable = false, length = 50)
    private String numeroResolucion;

    @Column(nullable = false, length = 255)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Resolucion categoria;

    // FK pendiente (módulo Usuarios)
    // Se agregará cuando exista la entidad Usuario
    // @ManyToOne
    // @JoinColumn(name = "autor_id")
    // private Usuario autor;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "version_actual")
    private Integer versionActual;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @Column(length = 50)
    private String estado;
}