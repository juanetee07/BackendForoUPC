package com.upc.demo.entity.noticias;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "noticias")

public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String cuerpo;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;

    @Enumerated(EnumType.STRING)
    private EstadoNoticia estado;

    /*Muchas noticias pueden pertenecer a una categoria*/
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaNoticia categoriaNoticia;

    /*Un usuario puede crear muchas noticias*/
    /*@ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;*/

    /*Una noticia puede tener varios archivos*/
    @OneToMany(mappedBy = "noticia")
    private List<ArchivoNoticia> archivos;

    /*Una noticia puede tener varias imagenes*/
    @OneToMany(mappedBy = "noticia")
    private List<ImagenNoticia> imagenes;
}