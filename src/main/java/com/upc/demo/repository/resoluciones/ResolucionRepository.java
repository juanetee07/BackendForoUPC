package com.upc.demo.repository.resoluciones;

import com.upc.demo.entity.resoluciones.CategoriaResolucion;
import com.upc.demo.entity.resoluciones.Resolucion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ResolucionRepository extends JpaRepository<Resolucion, Long> {

    /**
     * Busca una resolución utilizando su número.
     *
     * Ejem
     * RES-2026-001
     *
     * Se utiliza principalmente para:
     * - Consultas específicas.
     * - Verificar si una resolución ya existe.
     */
    Optional<Resolucion> findByNumeroResolucion(String numeroResolucion);

    /**
     * Verifica si ya existe una resolución con ese número.
     */
    boolean existsByNumeroResolucion(String numeroResolucion);

    /**
     * Obtiene todas las resoluciones según su estado.
     */
    List<Resolucion> findByEstado(String estado);

    /**
     * Busca resoluciones cuyo título contenga
     * una palabra o frase determinada.
     */
    List<Resolucion> findByTituloContaining(String titulo);

    /**
     * Busca resoluciones pertenecientes
     * a una categoría específica.
     */
    List<Resolucion> findByCategoria(CategoriaResolucion categoria);

    /**
     * Busca todas las resoluciones creadas
     * por un usuario determinado.
     *
     * Se utiliza principalmente para:
     * - Secretaría
     * - Administradores
     * - Auditorías
     */
    List<Resolucion> findByAutorIdUsuario(Long idUsuario);

    /**
     * Permite filtrar resoluciones
     * por rango de fechas.
     *
     * Ejemplo:
     * Desde: 01/01/2026
     * Hasta: 31/12/2026
     */
    List<Resolucion> findByFechaCreacionBetween(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin
    );
}