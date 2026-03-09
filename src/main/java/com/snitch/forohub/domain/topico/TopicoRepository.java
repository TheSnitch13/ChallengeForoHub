package com.snitch.forohub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    @Query("""
            SELECT t
            FROM Topico t
            WHERE (:nombreCurso IS NULL OR t.curso.nombre = :nombreCurso)
            AND (:anio IS NULL OR YEAR(t.fechaCreacion) = :anio)
            """)
    Page<Topico> filtrarPorCursoYAnio(String nombreCurso, Integer anio, Pageable paginacion);
}