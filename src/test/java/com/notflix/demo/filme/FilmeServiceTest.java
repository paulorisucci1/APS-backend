package com.notflix.demo.filme;

import com.notflix.demo.exceptions.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class FilmeServiceTest {

    private Filme film;

    @InjectMocks
    private FilmeService filmeService;

    @Mock
    private FilmeRepository filmeRepository;

    @Before
    public void setup() {

        this.film = Filme.builder()
                .id(1L)
                .titulo("O rei leão")
                .descricao("Um leão rei")
                .urlImagem("qualquerUrl.png")
                .dataLancamento(LocalDate.now())
                .build();
    }

    @Test
    public void shouldFindFilmById() {

        Mockito.when(filmeRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(this.film));

        final var foundFilm = filmeService.findById(this.film.getId());

        validateFilme(foundFilm);
    }

    @Test
    public void shouldThrowAnExceptionWhenFilmNotFound() {

        Mockito.when(filmeRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> filmeService.findById(this.film.getId())).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    public void shouldCreateFilmeSuccessfully() {

        Mockito.when(filmeRepository.save(Mockito.any(Filme.class))).thenReturn(this.film);

        Filme createdFilm = filmeService.create(this.film);

        validateFilme(createdFilm);
    }

    @Test
    public void shouldListFilmesSuccessfully() {

        Mockito.when(filmeRepository.findAll()).thenReturn(List.of(this.film));

        List<Filme> filmeList = filmeService.list();

        filmeList.forEach(this::validateFilme);
    }

    @Test
    public void shouldDelete() {
        filmeService.deleteFilme(1L);
        Mockito.verify(filmeRepository).deleteById(Mockito.anyLong());
    }

    @Test
    public void shouldUpdateFilmSuccessfully() {

        final var originalFilm = Filme.builder()
                .id(2L)
                .build();

        Mockito.when(filmeRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(originalFilm));
        Mockito.when(filmeRepository.save(Mockito.any(Filme.class))).thenReturn(this.film);

        final var updatedFilme = filmeService.updateFilme(originalFilm.getId(), this.film);

        validateFilme(updatedFilme);

    }

    private void validateFilme(Filme film) {
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(film.getId()).isEqualTo(this.film.getId());
            softly.assertThat(film.getTitulo()).isEqualTo(this.film.getTitulo());
            softly.assertThat(film.getDuracao()).isEqualTo(this.film.getDuracao());
            softly.assertThat(film.getDescricao()).isEqualTo(this.film.getDescricao());
            softly.assertThat(film.getUrlImagem()).isEqualTo(this.film.getUrlImagem());
            softly.assertThat(film.getDataLancamento()).isEqualTo(this.film.getDataLancamento());
        });

    }
}
