package com.upc.entity.resoluciones;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "resolucion_etiquetas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResolucionEtiqueta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resolucion_etiqueta")
    private Long idResolucionEtiqueta;

    @ManyToOne
    @JoinColumn(name = "resolucion_id", nullable = false)
    private Resolucion resolucion;

    @ManyToOne
    @JoinColumn(name = "etiqueta_id", nullable = false)
    private Etiqueta etiqueta;
}