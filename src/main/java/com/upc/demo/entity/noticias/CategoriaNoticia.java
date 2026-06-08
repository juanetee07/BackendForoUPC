package com.upc.demo.entity.noticias;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "categorias_noticias")

public class CategoriaNoticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nombre;

    /*Una categoría puede tener muchas noticias*/
    @OneToMany(mappedBy = "categoriaNoticia")
    private List<Noticia> noticias;
}
