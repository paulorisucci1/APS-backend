package com.notflix.demo.filme;

import com.notflix.demo.exceptions.EntityAlreadyExistException;
import com.notflix.demo.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;

    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public Filme create(Filme filme) {
        verifyIfFilmesTituloAlreadyExists(filme);
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

        verifyIfFilmesTituloAlreadyExists(updatedFilme);

        foundFilme.update(updatedFilme);

        return filmeRepository.save(foundFilme);
    }

    public void deleteFilme(Long id) {

        this.filmeRepository.deleteById(id);
    }

    void verifyIfFilmesTituloAlreadyExists(Filme filme) {
        final var foundFilme = filmeRepository.findByTitulo(filme.getTitulo());
        if(Objects.nonNull(foundFilme) && !Objects.equals(foundFilme.getId(), filme.getId())) {
            throw new EntityAlreadyExistException("Já existe um filme com o título informado.");
        }
    }
}
