package com.notflix.demo.filme;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.notflix.demo.filme.FilmeController.PATH_FILMES;

@RestController
@RequestMapping(value = PATH_FILMES)
public class FilmeController {

    static final String PATH_FILMES = "/api/filmes";

    private final FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @PostMapping
    public ResponseEntity<Filme> createFilm(@RequestBody Filme filme) {
        return ResponseEntity.ok(this.filmeService.create(filme));
    }

    @GetMapping
    public ResponseEntity<List<Filme>> listFilms() {
        return ResponseEntity.ok(this.filmeService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> findFilm(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.filmeService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filme> updateFilm(@PathVariable("id") Long id, @RequestBody Filme filme) {
        return ResponseEntity.ok(this.filmeService.updateFilme(id, filme));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable("id") Long id) {
        this.filmeService.deleteFilme(id);
        return ResponseEntity.noContent().build();
    }
}
