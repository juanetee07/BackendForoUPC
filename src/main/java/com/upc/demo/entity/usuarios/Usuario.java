package com.upc.demo.entity.usuarios;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String dni;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String contraseña;

    @Column(nullable = false)
    private String estado;

    @Column(name = "estado_solicitud")
    private String estadoSolicitud;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "ultimo_acceso")
    private LocalDateTime ultimoAcceso;

    @ManyToMany
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol")
    )
    private Set<Rol> roles;

    @OneToMany(mappedBy = "usuario")
    private List<Sesion> sesiones;

    @OneToMany(mappedBy = "usuario")
    private List<Auditoria> auditorias;
}