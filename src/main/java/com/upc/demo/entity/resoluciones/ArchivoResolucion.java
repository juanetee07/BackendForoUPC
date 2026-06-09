package com.upc.demo.entity.resoluciones;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "archivos_resoluciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArchivoResolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_archivo")
    private Long idArchivo;

    @ManyToOne
    @JoinColumn(name = "resolucion_id", nullable = false)
    private Resolucion resolucion;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(nullable = false, length = 500)
    private String url;

    @Column(nullable = false, length = 100)
    private String tipo;
}