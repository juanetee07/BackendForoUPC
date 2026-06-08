package com.upc.demo.entity.resoluciones;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "carreras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrera")
    private Long idCarrera;

    @Column(nullable = false, length = 100)
    private String nombre;
}