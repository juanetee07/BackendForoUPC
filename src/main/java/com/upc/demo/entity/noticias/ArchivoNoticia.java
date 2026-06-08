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
@Table(name = "archivos_noticias")
public class ArchivoNoticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nombre;
    private String url;
    private String tipo;

    /*Muchos archivos pueden pertenecer a una noticia*/
    @ManyToOne
    @JoinColumn(name="noticia_id")
    private Noticia noticia;
}



