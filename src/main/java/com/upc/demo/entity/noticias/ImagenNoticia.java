package com.upc.demo.entity.noticias;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "imagenes_noticias")

public class ImagenNoticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String url;
    private String epigrafe;

    /*Muchas imagenes pueden pertenecer a una noticia*/
    @ManyToOne
    @JoinColumn(name="noticia_id")
    private Noticia noticia;

}
