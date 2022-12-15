package com.notflix.demo.filme;

import com.notflix.demo.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;

    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public Filme create(Filme filme) {
        return this.filmeRepository.save(filme);
    }

    public List<Filme> list() {
        return this.filmeRepository.findAll();
    }

    public Filme findById(Long id) {
        return this.filmeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Filme não encontrado!"));
    }

    public Filme updateFilme(Long id, Filme updatedFilme) {

        final var foundFilme = findById(id);

        foundFilme.update(updatedFilme);

        return filmeRepository.save(foundFilme);
    }

    public void deleteUser(Long id) {

        final var foundFilme = findById(id);

        this.filmeRepository.delete(foundFilme);
    }

}
