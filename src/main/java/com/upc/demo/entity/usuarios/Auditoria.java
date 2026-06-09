package com.upc.demo.entity.usuarios;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "auditoria")
@Getter
@Setter
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAuditoria;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private String entidad;

    private Long idRegistro;

    private String accion;

    private LocalDateTime fechaHora;

    private String detalle;

    private String valorAnterior;

    private String valorNuevo;
}