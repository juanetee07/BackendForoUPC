package com.upc.demo.entity.resoluciones;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "etiquetas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Etiqueta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_etiqueta")
    private Long idEtiqueta;

    @Column(nullable = false, length = 100)
    private String nombre;
}