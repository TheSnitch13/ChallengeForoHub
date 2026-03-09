package com.snitch.forohub.controller;

import com.snitch.forohub.domain.curso.Curso;
import com.snitch.forohub.domain.curso.CursoRepository;
import com.snitch.forohub.domain.topico.*;
import com.snitch.forohub.domain.usuario.Usuario;
import com.snitch.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public TopicoController(TopicoRepository topicoRepository,
                            UsuarioRepository usuarioRepository,
                            CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    @PostMapping
    public ResponseEntity<String> registrar(@RequestBody @Valid DatosRegistroTopico datos) {

        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            return ResponseEntity.badRequest().body("Ya existe un tópico con el mismo título y mensaje");
        }

        Usuario autor = usuarioRepository.getReferenceById(datos.autorId());
        Curso curso = cursoRepository.getReferenceById(datos.cursoId());

        Topico topico = new Topico(datos, autor, curso);
        topicoRepository.save(topico);

        return ResponseEntity.ok("Tópico registrado correctamente");
    }

    @GetMapping
    public Page<DatosListadoTopico> listar(
            @PageableDefault(size = 10, sort = "fechaCreacion") Pageable paginacion,
            @RequestParam(required = false) String nombreCurso,
            @RequestParam(required = false) Integer anio
    ) {
        if (nombreCurso != null || anio != null) {
            return topicoRepository
                    .filtrarPorCursoYAnio(nombreCurso, anio, paginacion)
                    .map(DatosListadoTopico::new);
        }

        return topicoRepository.findAll(paginacion).map(DatosListadoTopico::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopico> detallar(@PathVariable Long id) {
        var topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var topico = topicoOptional.get();
        return ResponseEntity.ok(new DatosListadoTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<String> actualizar(@PathVariable Long id,
                                             @RequestBody @Valid DatosActualizarTopico datos) {

        var topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (topicoRepository.existsByTituloAndMensajeAndIdNot(datos.titulo(), datos.mensaje(), id)) {
            return ResponseEntity.badRequest().body("Ya existe un tópico con el mismo título y mensaje");
        }

        Usuario autor = usuarioRepository.getReferenceById(datos.autorId());
        Curso curso = cursoRepository.getReferenceById(datos.cursoId());

        Topico topico = topicoOptional.get();
        topico.actualizarDatos(datos, autor, curso);

        return ResponseEntity.ok("Tópico actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        var topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}