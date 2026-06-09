package com.upc.demo.entity.usuarios;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "sesiones")
@Getter
@Setter
public class Sesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSesion;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private String tokenJwt;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaExpiracion;

    private Boolean activa;
}