package com.upc.demo.entity.usuarios;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;

    @Column(nullable = false, unique = true)
    private String nombre;

    @ManyToMany(mappedBy = "roles")
    private Set<Usuario> usuarios;
}